package com.example.location_demo_app.ui_layer.screens.main_screen_components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.location_demo_app.ui_layer.theme.fabBackground
import com.example.location_demo_app.ui_layer.theme.fabColor

/**
 * Compose function for the RefreshButton compose component.
 *
 * @param onRefreshClick When button is clicked.
 */
@Composable
fun RefreshFab(onRefreshClick: () -> Unit) {
    FloatingActionButton(
        onClick = onRefreshClick,
        modifier = Modifier.size(70.dp, 70.dp),
        containerColor = fabBackground,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 8.dp, pressedElevation = 5.dp
        )
    ) {
        Icon(
            modifier = Modifier.size(36.dp, 36.dp),
            imageVector = Icons.Filled.Refresh,
            contentDescription = "Refresh",
            tint = fabColor
        )
    }
}