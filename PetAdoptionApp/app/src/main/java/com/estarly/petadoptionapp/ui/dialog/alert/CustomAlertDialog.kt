package com.estarly.petadoptionapp.ui.dialog.alert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomAlertDialog(
    title: String,
    message: String,
    show : Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit
) {
    if(!show){
        return
    }
    AlertDialog(
        onDismissRequest = {},
        title = { Text(text = title) },
        text = { Text(text = message) },
        modifier = Modifier.fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White),
        confirmButton = {
            TextButton(onClick = { onConfirm()}) {
                Text(text = "Accept")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss()}) {
                Text(text = "Cancel")
            }
        }
    )
}