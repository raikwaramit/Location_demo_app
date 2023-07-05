package com.example.location_demo_app.ui_layer.screens

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.location_demo_app.data.DialogData
import com.example.location_demo_app.ui_layer.Utils.ScreenConstants

/**
 * Dialog box for asking the required permission if the device do not have necessary permissions.
 *
 * @param navController Used to launch main screen when user dismiss the dialog.
 * @param permissionRequest This is used to invoke the permission setting so that user can grant permission.
 */
@Composable
fun PermissionDialog(navController: NavController, permissionRequest: () -> Unit) {
    DialogBox(
        dialogData = DialogData(
            shouldBeShown = true,
            title = "Need permission",
            text = "Please provide the location access.",
            confirmButtonText = "Provide access",
            dismissButtonText = "Dismiss",
            confirmButtonAction = {
                permissionRequest()// Fire permission request.
            },
            dismissButtonAction = {
                navController.navigate(ScreenConstants.MAIN_SCREEN)
            },
        )
    )
}
