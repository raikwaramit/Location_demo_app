package com.example.location_demo_app.ui_layer.screens.main_screen_components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.location_demo_app.data.MapPosition
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

/**
 * Main content screen compose having the map and bottom sheet.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent() {
    // State required for the Google map compose component.
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(0.0, 0.0), 1f)
    }

    // Stated for the bottom sheet.
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberBottomSheetScaffoldState()

    // lambda to handle the position updates on map click, button click, or when user enter the coordinates.
    val mapPositionClicked: (MapPosition) -> Unit = {
        coroutineScope.launch { scaffoldState.bottomSheetState.partialExpand() }
        cameraPositionState.move(
            CameraUpdateFactory.newLatLng(
                LatLng(
                    it.latitude, it.longitude
                )
            )
        )
    }

    // Bottom sheet
    BottomSheetScaffold(scaffoldState = scaffoldState,
        sheetPeekHeight = 32.dp,
        sheetContent = { SheetContent(cameraPositionState, mapPositionClicked) }) {
        GoogleMap(modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(isMyLocationEnabled = true),
            uiSettings = MapUiSettings(
                zoomGesturesEnabled = true,
                zoomControlsEnabled = false,
                myLocationButtonEnabled = true,
            ),
            onMyLocationClick = {
                mapPositionClicked(MapPosition(it.latitude, it.longitude))
            },
            onMapClick = {
                mapPositionClicked(MapPosition(it.latitude, it.longitude))
            })
        BackHandler() {
            coroutineScope.launch { scaffoldState.bottomSheetState.partialExpand() }
        }
    }
}

