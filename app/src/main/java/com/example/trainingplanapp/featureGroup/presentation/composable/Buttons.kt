package com.example.trainingplanapp.featureGroup.presentation.composable

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ChooseButton(
    modifier: Modifier = Modifier,
    onFirstButtonClicked: () -> Unit,
    onSecondButtonClicked: () -> Unit,
    isFirstButtonChosen: Boolean = true
) {
    Row(
        modifier = modifier
    ) {
        Button(
            onClick = { onFirstButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            elevation = null,
            shape = RoundedCornerShape(
                topStart = 8.dp,
                bottomStart = 8.dp,
                topEnd = 0.dp,
                bottomEnd = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (isFirstButtonChosen) MaterialTheme.colors.primary else MaterialTheme.colors.background
            )
        ) {
            Text(
                text = "Couch",
                style = MaterialTheme.typography.h5.copy(
                    color = if (isFirstButtonChosen) MaterialTheme.colors.background else MaterialTheme.colors.primary
                )
            )
        }
        Button(
            onClick = { onSecondButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            elevation = null,
            shape = RoundedCornerShape(
                topEnd = 8.dp,
                bottomEnd = 8.dp,
                topStart = 0.dp,
                bottomStart = 0.dp
            ),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (!isFirstButtonChosen) MaterialTheme.colors.primary else MaterialTheme.colors.background
            )
        ) {
            Text(
                text = "Gym goer",
                style = MaterialTheme.typography.h5.copy(
                    color = if (!isFirstButtonChosen) MaterialTheme.colors.background else MaterialTheme.colors.primary
                )
            )
        }
    }
}