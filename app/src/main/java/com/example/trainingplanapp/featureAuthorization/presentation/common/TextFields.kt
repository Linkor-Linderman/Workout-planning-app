package com.example.trainingplanapp.featureAuthorization.presentation.common

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AuthorizationTextField(
    modifier: Modifier = Modifier,
    value: String,
    placeholder: String,
    label: String,
    onValueChange: (String) -> Unit,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colors.primary,
            cursorColor = MaterialTheme.colors.primary,
            backgroundColor = Color.Transparent,
        ),
        label = {
            Text(
                text = label,
                color = MaterialTheme.colors.primaryVariant,
                fontSize = 14.sp
            )
        },
        textStyle = MaterialTheme.typography.body2.copy(color = MaterialTheme.colors.primary),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )
}