package com.example.location_demo_app.ui_layer.screens


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.location_demo_app.ui_layer.screens.main_screen_components.MainScreenContent
import com.example.location_demo_app.ui_layer.screens.main_screen_components.RefreshFab
import com.example.location_demo_app.ui_layer.theme.bottomBarColor
import com.example.location_demo_app.ui_layer.theme.fabBackground
import com.example.location_demo_app.ui_layer.theme.fabColor
import com.example.location_demo_app.ui_layer.theme.themeColor

/**
 * Main screen compose layout showing the map, navigation top app bar, navigation bottom bar, FAB
 * button etc.
 */
@Preview
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    var selectedItem by remember { mutableIntStateOf(0) }

    Scaffold(topBar = {
        // Top app bar.
        TopAppBar(title = { Text("Location demo app") }, navigationIcon = {
            IconButton(onClick = {}) {
                Icon(Icons.Filled.Close, contentDescription = "Back button")
            }
        }, colors = TopAppBarColors(
            themeColor, fabBackground, fabColor, fabColor, fabColor
        ), actions = {
            // Top app bar menu item.
            IconButton(onClick = { /* doSomething() */ }) {
                Icon(
                    Icons.Filled.LocationOn, contentDescription = "Location on"
                )
            }
        })
    }, floatingActionButton = {
        // Floating action button.
        RefreshFab(onRefreshClick = { })
    }, bottomBar = {
        // bottom Navigation bar having two icons i.e, Main, Settings.
        NavigationBar(
            containerColor = bottomBarColor, tonalElevation = 5.dp
        ) {
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Home, contentDescription = "Home"
                    )
                },
                label = { Text(text = "Home") },
                selected = selectedItem == 0,
                onClick = { selectedItem = 0 },
                alwaysShowLabel = false
            )
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = Icons.Filled.Settings, contentDescription = "Settings"
                    )
                },
                label = { Text(text = "Settings") },
                selected = selectedItem == 1,
                onClick = { selectedItem = 1 },
                alwaysShowLabel = false
            )
        }
    }, content = {
        // Screen content hosting map and bottomSheet.
        if (selectedItem == 0) {
            // Main screen
            Surface(modifier = Modifier.padding(it)) {
                MainScreenContent()
            }
        } else {
            // Setting screen.
            Surface(modifier = Modifier.padding(it)) {

            }
        }
    })
}
