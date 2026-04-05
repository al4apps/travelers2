package com.al4apps.travelers2.design_system.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton as MaterialTextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.intabia.platform.theme.AppTheme

object LAppButtonStyles {
    val shape = RoundedCornerShape(14.dp)
    val padding = PaddingValues(horizontal = 16.dp, vertical = 12.dp)
    val minWidth = 120.dp
    val minHeight = 42.dp
}

@Composable
fun PrimaryButton(
    text: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = LAppButtonStyles.shape,
        contentPadding = LAppButtonStyles.padding,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.button.primary,
            contentColor = AppTheme.colors.button.primaryTextColor,
            disabledContainerColor = AppTheme.colors.button.primaryDisable,
            disabledContentColor = AppTheme.colors.text.secondary
        ),
        modifier = modifier
            .widthIn(min = LAppButtonStyles.minWidth)
            .height(LAppButtonStyles.minHeight)
    ) {
        Text(text = text, style = AppTheme.typography.subtitle)
    }
}

@Composable
fun SecondaryButton(
    text: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val textColor = AppTheme.colors.text.primary
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = LAppButtonStyles.shape,
        contentPadding = LAppButtonStyles.padding,
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.background.default,
            contentColor = AppTheme.colors.text.primary,
            disabledContainerColor = AppTheme.colors.background.default,
            disabledContentColor = AppTheme.colors.text.disabledSecondary
        ),
        border = BorderStroke(1.dp, textColor),
        modifier = modifier
            .widthIn(min = LAppButtonStyles.minWidth)
            .height(LAppButtonStyles.minHeight)
    ) {
        Text(text = text, style = AppTheme.typography.subtitle)
    }
}

@Composable
fun TextButton(
    text: String,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    MaterialTextButton(
        onClick = onClick,
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = AppTheme.colors.text.primary,
            disabledContentColor = AppTheme.colors.text.disabledSecondary
        ),
        modifier = modifier
    ) {
        Text(text = text, style = AppTheme.typography.subtitle)
    }
}
