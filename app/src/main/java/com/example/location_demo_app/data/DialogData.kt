package com.example.location_demo_app.data

/**
 * Data class for holding the data required to show the dialog.
 *
 * @property shouldBeShown Tells if dialog should be shown or not.
 * @property title Title text for the dialog.
 * @property text Details text for the dialog.
 * @property confirmButtonText Text for the primary/confirmation button in dialog.
 * @property dismissButtonText Text for the secondary/dismiss button in dialog.
 * @property confirmButtonAction Invoked when confirmation button is clicked.
 * @property dismissButtonAction Invoked when dismiss button is clicked.
 */
data class DialogData(
    val shouldBeShown: Boolean = false,
    val title: String = "",
    val text: String = "",
    val confirmButtonText: String = "",
    val dismissButtonText: String = "",
    val confirmButtonAction: () -> Unit = {},
    val dismissButtonAction: () -> Unit = {},
)
