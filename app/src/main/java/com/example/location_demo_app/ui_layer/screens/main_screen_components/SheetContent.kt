package com.example.location_demo_app.ui_layer.screens.main_screen_components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.location_demo_app.data.MapPosition
import com.google.maps.android.compose.CameraPositionState

/**
 * Composable for sheet content having textfield to enter the latitude, latitude and two buttons
 * to check
 *
 * @param cameraPositionState Takes camera position state to get the live pointed location by the map.
 * @param putLocationOnMap Lambda function if user wants to see coordinates in map.
 *
 */
@Composable
fun SheetContent(
    cameraPositionState: CameraPositionState,
    putLocationOnMap: (MapPosition) -> Unit
) {
    var latitude by rememberSaveable { mutableStateOf("0.0") }
    var longitude by rememberSaveable { mutableStateOf("0.0") }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Latitude")
        TextField(value = latitude, singleLine = true, onValueChange = {
            latitude = it
        })

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Longitude")
        TextField(value = longitude, singleLine = true, onValueChange = {
            longitude = it
        })

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(onClick = {
                latitude = cameraPositionState.position.target.latitude.toString()
                longitude = cameraPositionState.position.target.longitude.toString()
            }) { Text(text = "Get current position") }

            Button(onClick = {
                putLocationOnMap(
                    MapPosition(
                        latitude.toDouble(),
                        longitude.toDouble()
                    )
                )
            }) {
                Text(text = "Check positions on map")
            }
        }
    }
}
