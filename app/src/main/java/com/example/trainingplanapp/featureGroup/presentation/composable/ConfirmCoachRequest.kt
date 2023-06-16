package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmCoachRequestDialog(
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        backgroundColor = MaterialTheme.colors.background,
        title = {
            Text(
                text = "Your coaching request has been sent.",
                color = MaterialTheme.colors.primary
            )
        },
        text = {
            Text(
                "Please wait for an answer and start training",
                color = MaterialTheme.colors.primary
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = onDismissRequest,
                    elevation = null
                ) {
                    Text(
                        "OK",
                        style = MaterialTheme.typography.button,
                        color = MaterialTheme.colors.background
                    )
                }
            }
        }
    )
}