package com.al4apps.travelers2.design_system.input

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import org.jetbrains.compose.resources.stringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.auth_email_label
import com.al4apps.travelers2.theme.AppTheme

@Composable
fun OutlinedEmailTextField(
    value: String,
    isError: Boolean,
    errorText: UiText?,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        label = { Text(text = stringResource(Res.string.auth_email_label)) },
        singleLine = true,
        isError = isError,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        shape = RoundedCornerShape(16.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = AppTheme.colors.background.secondary,
            focusedContainerColor = AppTheme.colors.background.secondary,
            focusedBorderColor = AppTheme.colors.border.secondary,
            unfocusedBorderColor = AppTheme.colors.border.divider,
            errorBorderColor = AppTheme.colors.icon.error,
            focusedLabelColor = AppTheme.colors.text.secondary,
            unfocusedLabelColor = AppTheme.colors.text.secondary,
            errorLabelColor = AppTheme.colors.icon.error,
            cursorColor = AppTheme.colors.text.primary
        )
    )
    if (isError && errorText != null) {
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = errorText.getString(),
            style = AppTheme.typography.caption,
            color = AppTheme.colors.icon.error,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
