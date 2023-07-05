package com.example.location_demo_app.ui_layer.Utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.location_demo_app.data.PermissionResult

/**
 * An extension function to check the location services of the device and returns result accordingly.
 */
fun Context.checkLocationServiceEnabled(): PermissionResult {
    val locationManager = this.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    var gpsEnabled = false
    var networkEnabled = false

    try {
        gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    } catch (ex: Exception) {

    }

    try {
        networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    } catch (ex: Exception) {
    }

    when {
        !gpsEnabled && !networkEnabled -> {
            return PermissionResult.AllPermission(false)
        }

        !gpsEnabled -> {
            return PermissionResult.GpsOff(
                gpsError = "Your device cant get GPS location.",
                settingIntent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            )
        }

        !networkEnabled -> {
            return PermissionResult.NetworkOff(
                networkError = "Your device don't have network connection.",
                settingIntent = Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS)
            )
        }

        else -> {
            return PermissionResult.AllPermission(haveBothPermission = true)
        }
    }
}

/**
 * A extension method to check the permission(Might cleanup later).
 */
fun Context.checkPermissions(): Boolean {
    return (ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.ACCESS_COARSE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED) && (ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED)
}

/**
 * A extension method to check the permission rationale(Might cleanup later).
 */
fun Activity.checkPermissionRationale() = ActivityCompat.shouldShowRequestPermissionRationale(
    this, android.Manifest.permission.ACCESS_FINE_LOCATION
) && ActivityCompat.shouldShowRequestPermissionRationale(
    this, android.Manifest.permission.ACCESS_COARSE_LOCATION
)