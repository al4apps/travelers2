package com.al4apps.travelers2.design_system.input

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
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
    onClick: (() -> Unit)? = null,
    onIconClick: (() -> Unit)? = null,
    onValueChange: (String) -> Unit,
    interactionSource: MutableInteractionSource? = null,
) {
    val cornerShape = RoundedCornerShape(14.dp)
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .clip(cornerShape)
            .then(
                if (onClick != null) {
                    Modifier
                        .clickable(onClick = { onClick() })
                } else {
                    Modifier
                },
            ),
        enabled = enabled,
        readOnly = readOnly,
        singleLine = true,
        interactionSource = interactionSource,
        shape = cornerShape,
        textStyle = AppTheme.typography.body1.copy(
            color = AppTheme.colors.text.default,
        ),
        placeholder = {
            Text(
                text = placeholder.getString(),
                style = AppTheme.typography.body1,
                color = AppTheme.colors.text.tertiary,
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null,
                tint = AppTheme.colors.icon.grayDark,
                modifier = Modifier.clickable(onClick = { onIconClick?.invoke() })
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
