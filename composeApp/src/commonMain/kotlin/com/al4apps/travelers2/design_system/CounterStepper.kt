package com.al4apps.travelers2.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.painterResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.ic_minus
import travelers2.composeapp.generated.resources.ic_plus

@Composable
fun CounterStepper(
    value: String,
    canDecrement: Boolean,
    canIncrement: Boolean,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        StepperButton(
            icon = Res.drawable.ic_minus,
            enabled = canDecrement,
            onClick = onDecrement,
        )
        Text(
            text = value,
            style = AppTheme.typography.h2,
            color = AppTheme.colors.text.default,
            textAlign = TextAlign.Center,
            modifier = Modifier.widthIn(min = 56.dp),
        )
        StepperButton(
            icon = Res.drawable.ic_plus,
            enabled = canIncrement,
            onClick = onIncrement,
        )
    }
}

@Composable
private fun StepperButton(
    icon: org.jetbrains.compose.resources.DrawableResource,
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
            .size(36.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable(enabled = enabled, onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(18.dp),
        )
    }
}
