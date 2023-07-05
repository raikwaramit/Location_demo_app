package com.example.location_demo_app.data

import android.content.Intent

/**
 * Sealed interface for the permission check results.
 */
sealed interface PermissionResult {

    /**
     * Data class holding the gps and network check cumulative result.
     *
     * @property haveBothPermission Tells if app have both permissions.
     */
    data class AllPermission(val haveBothPermission: Boolean) : PermissionResult

    /**
     * Data class holding the gps off related results.
     *
     * @property gpsError Error string that need to be shown in dialog.
     * @property settingIntent Intent to launch the setting for GPS access permission.
     */
    data class GpsOff(val gpsError: String, val settingIntent: Intent) : PermissionResult

    /**
     * Data class holding the network off related results.
     *
     * @property networkError Error string that need to be shown in dialog.
     * @property settingIntent Intent to launch the setting for network access permission.
     */
    data class NetworkOff(val networkError: String, val settingIntent: Intent) : PermissionResult
}