package com.ammaryasser.tourating.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType


@Composable
fun FormTextField(
    icon: ImageVector,
    labelResId: Int,
    placeholderResId: Int,
    value: Int,
    isNumber: Boolean = true,
    isOptional: Boolean = false,
    onValueChange: (Int) -> Unit
) = FormTextField(
    icon,
    labelResId,
    placeholderResId,
    value.toString(),
    isNumber,
    isOptional
) {
    onValueChange(it.takeIf { it.isNotEmpty() }?.toInt() ?: 0)
}


@Composable
fun FormTextField(
    icon: ImageVector,
    labelResId: Int,
    placeholderResId: Int,
    value: String,
    isNumber: Boolean = false,
    isOptional: Boolean = false,
    isMultiLine: Boolean = false,
    onValueChange: (String) -> Unit
) = FormTextField(
    icon,
    stringResource(labelResId),
    stringResource(placeholderResId),
    value,
    isNumber,
    isOptional,
    isMultiLine,
    onValueChange
)


@Composable
private fun FormTextField(
    icon: ImageVector,
    label: String,
    placeholder: String,
    value: String,
    isNumber: Boolean = false,
    isOptional: Boolean = false,
    isMultiLine: Boolean = false,
    onValueChange: (String) -> Unit
) {
    var notifyError by remember { mutableStateOf(false) }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = {
            onValueChange(it)
            notifyError = !isOptional and it.isBlank()
        },
        leadingIcon = {
            Icon(icon, "")
        },
        label = {
            Text(label)
        },
        placeholder = {
            Text(placeholder)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.Sentences,
            keyboardType = if (isNumber) KeyboardType.Number else KeyboardType.Text
        ),
        singleLine = !isMultiLine,
        isError = notifyError,
    )
}