package com.example.location_demo_app.ui_layer.screens

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.location_demo_app.data.DialogData

/**
 * A generic composable for emitting a dialog ui.
 *
 * @param dialogData Takes [DialogData] to show the dialog.
 */
@Composable
fun DialogBox(dialogData: DialogData) {
    var openDialog by remember { mutableStateOf(dialogData.shouldBeShown) }
    if (openDialog) {
        AlertDialog(onDismissRequest = {
            openDialog = false
        },
            title = { Text(text = dialogData.title) },
            text = { Text(text = dialogData.text) },
            confirmButton = {
                TextButton(onClick = {
                    openDialog = false
                    dialogData.confirmButtonAction()
                }) {
                    Text(dialogData.confirmButtonText)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    openDialog = false
                    dialogData.dismissButtonAction()

                }) {
                    Text(text = dialogData.dismissButtonText)
                }
            })
    }
}
