package com.example.location_demo_app.ui_layer.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.location_demo_app.R
import kotlinx.coroutines.delay

/**
 * Splash compose screen for the main activity that will be shown at the starting of the launch.
 *
 * @param navigateToMainScreen Lambda function that will be invoked to launch the main screen.
 */
@Composable
fun SplashScreen(navigateToMainScreen: () -> Unit) {
    LaunchedEffect(Unit) {
        // Simulate a loading delay
        delay(2000)
        navigateToMainScreen()
    }

    // Splash screen compose
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_share_location_24),
            contentDescription = "Splash Screen",
            modifier = Modifier
                .size(200.dp)
                .offset(y = (-50).dp) // Adjust the offset as per your design
        )
    }
}
