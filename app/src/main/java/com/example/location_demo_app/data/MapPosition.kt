package com.example.location_demo_app.data

/**
 * Data class for holding the map coordinated positions.
 *
 * @property latitude Holds latitude coordinates.
 * @property longitude Holds longitude coordinates.
 */
data class MapPosition(
    var latitude: Double = 0.0, var longitude: Double = 0.0
)