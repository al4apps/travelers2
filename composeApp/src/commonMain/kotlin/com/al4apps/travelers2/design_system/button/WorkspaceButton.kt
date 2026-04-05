package com.al4apps.travelers2.design_system.button

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.intabia.platform.theme.AppTheme

@Composable
fun WorkspaceButton(
    title: String,
    subtitle: String? = null,
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppTheme.colors.background.secondary,
            contentColor = AppTheme.colors.text.primary,
            disabledContainerColor = AppTheme.colors.background.secondary,
            disabledContentColor = AppTheme.colors.text.disabledSecondary
        ),
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = 72.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.primary
            )
            if (!subtitle.isNullOrBlank()) {
                Text(
                    text = subtitle,
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.secondary
                )
            }
        }
    }
}
