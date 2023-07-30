package com.example.location_demo_app.ui_layer

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.location_demo_app.data.DialogData
import com.example.location_demo_app.data.PermissionResult
import com.example.location_demo_app.ui_layer.Utils.ScreenConstants
import com.example.location_demo_app.ui_layer.Utils.checkLocationServiceEnabled
import com.example.location_demo_app.ui_layer.screens.DialogBox
import com.example.location_demo_app.ui_layer.screens.MainScreen
import com.example.location_demo_app.ui_layer.screens.PermissionDialog
import com.example.location_demo_app.ui_layer.screens.SplashScreen
import com.example.location_demo_app.ui_layer.theme.Location_demo_appTheme

/**
 * Main screen of the app hosting many other screens like Splash screen, main map screen, settings screen.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Setting compose content on activity.
        setContent {
            Location_demo_appTheme {
                InitNavigator() // Start showing the screens.

                var doHavePermission: Boolean = false
                var errorText: String = ""
                var settingIntent: Intent = Intent()

                // Checks for the required permissions.
                val permissionCheckResult = checkLocationServiceEnabled()

                with(permissionCheckResult) {
                    if (this is PermissionResult.AllPermission && this.haveBothPermission) {
                        doHavePermission = true
                    } else {
                        when (this) {
                            is PermissionResult.NetworkOff -> {
                                errorText = this.networkError
                                settingIntent = this.settingIntent
                            }

                            is PermissionResult.GpsOff -> {
                                errorText = this.gpsError
                                settingIntent = this.settingIntent
                            }

                            else -> {
                                errorText = "Both permission are not present."
                                settingIntent = Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS)
                            }
                        }
                    }
                }

                // To show the dialog for asking required permissions from the user for accessing internet and location if not already granted.
                if (!doHavePermission) {
                    DialogBox(
                        dialogData = DialogData(shouldBeShown = true,
                            title = "Error",
                            text = errorText,
                            confirmButtonText = "Fix error",
                            dismissButtonText = "Cancel",
                            confirmButtonAction = {
                                startActivity(settingIntent)
                            })
                    )
                }
            }
        }
    }
}

/**
 * Compose ui for showing different types of screen on the activity using navigation.
 */
@Composable
private fun InitNavigator() {
    val navController = rememberNavController()
    val checkPermissionAndLaunchMainScreen: () -> Unit = {
        if (true) {
            navController.navigate(ScreenConstants.MAIN_SCREEN)
        } else {
            TODO("Need to reiterate this and update accordingly.")
            navController.navigate(ScreenConstants.ASK_PERMISSION)
        }
    }

    NavHost(
        navController = navController, startDestination = ScreenConstants.SPLASH_SCREEN
    ) {
        composable(ScreenConstants.SPLASH_SCREEN) {
            SplashScreen(
                checkPermissionAndLaunchMainScreen
            )
        }
        composable(ScreenConstants.MAIN_SCREEN) { MainScreen() }
        composable(ScreenConstants.ASK_PERMISSION) { PermissionDialog(navController) {} }
    }
}