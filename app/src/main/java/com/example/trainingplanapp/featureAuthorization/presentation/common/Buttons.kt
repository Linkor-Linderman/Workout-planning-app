package com.example.trainingplanapp.featureAuthorization.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FilledButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    Button(
        modifier = modifier,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary
        ),
        shape = RoundedCornerShape(8.dp),
        contentPadding = PaddingValues(vertical = 13.dp)
    ) {
        Text(
            modifier = Modifier.align(CenterVertically),
            text = text,
            color = MaterialTheme.colors.background,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun EmptyButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String
) {
    TextButton(
        modifier = modifier,
        onClick = { onClick() },
        contentPadding = PaddingValues(vertical = 13.dp),
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Text(
            modifier = Modifier.align(CenterVertically),
            text = text,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.button
        )
    }
}