package com.al4apps.travelers2.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.ic_minus
import travelers2.composeapp.generated.resources.ic_plus

private val BUTTON_SIZE: Dp = 36.dp
private val ICON_SIZE: Dp = 18.dp

/**
 * Two equal round stepper buttons with a value in between.
 *
 * Both buttons use [requiredSize] so they never get squished by the parent layout
 * regardless of available width.
 */
@Composable
fun CounterStepper(
    value: String,
    canDecrement: Boolean,
    canIncrement: Boolean,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier,
    valueWidth: Dp = 56.dp,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        StepperButton(
            icon = Res.drawable.ic_minus,
            enabled = canDecrement,
            onClick = onDecrement,
        )
        Box(
            modifier = Modifier.width(valueWidth),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = value,
                style = AppTheme.typography.h2,
                color = AppTheme.colors.text.default,
                textAlign = TextAlign.Center,
            )
        }
        StepperButton(
            icon = Res.drawable.ic_plus,
            enabled = canIncrement,
            onClick = onIncrement,
        )
    }
}

@Composable
private fun StepperButton(
    icon: DrawableResource,
    enabled: Boolean,
    onClick: () -> Unit,
) {
    val backgroundColor = if (enabled) {
        AppTheme.colors.button.primary
    } else {
        AppTheme.colors.button.primaryDisable
    }
    val tint = if (enabled) {
        AppTheme.colors.icon.white
    } else {
        AppTheme.colors.icon.grayDark
    }
    Box(
        modifier = Modifier
            .requiredSize(BUTTON_SIZE)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.requiredSize(ICON_SIZE),
        )
    }
}
