package com.al4apps.travelers2.design_system.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.asString
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun TravelersFormTextField(
    value: String,
    placeholder: UiText,
    icon: DrawableResource,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        enabled = enabled,
        readOnly = readOnly,
        singleLine = true,
        shape = RoundedCornerShape(14.dp),
        textStyle = AppTheme.typography.body1.copy(
            color = AppTheme.colors.text.default,
        ),
        placeholder = {
            Text(
                text = placeholder.asString(),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.text.tertiary,
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = AppTheme.colors.icon.grayDark,
            )
        },
        keyboardOptions = keyboardOptions,
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = AppTheme.colors.background.default,
            unfocusedContainerColor = AppTheme.colors.background.default,
            disabledContainerColor = AppTheme.colors.background.default,
            focusedBorderColor = AppTheme.colors.border.secondary,
            unfocusedBorderColor = AppTheme.colors.border.divider,
            disabledBorderColor = AppTheme.colors.border.divider,
            focusedTextColor = AppTheme.colors.text.default,
            unfocusedTextColor = AppTheme.colors.text.default,
            disabledTextColor = AppTheme.colors.text.default,
            cursorColor = AppTheme.colors.text.default,
        ),
    )
}
