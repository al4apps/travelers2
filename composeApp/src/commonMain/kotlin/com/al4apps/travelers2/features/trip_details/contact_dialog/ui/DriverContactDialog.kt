package com.al4apps.travelers2.features.trip_details.contact_dialog.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.dialog.TravelersBaseContentDialog
import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactDialogComponent
import com.al4apps.travelers2.features.trip_details.contact_dialog.model.DriverContactActionStyle
import com.al4apps.travelers2.features.trip_details.contact_dialog.model.DriverContactActionUiState
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState

private val telegramColor = Color(0, 136, 204)
@Composable
fun DriverContactDialog(
    component: DriverContactDialogComponent,
) {
    val uiState = component.contactActionsState.subscribeAsState().value

    TravelersBaseContentDialog(component = component) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            uiState.actions.forEach { action ->
                DriverContactActionButton(
                    uiState = action,
                    onClick = { component.onActionClick(action.action) },
                )
            }
        }
    }
}

@Composable
private fun DriverContactActionButton(
    uiState: DriverContactActionUiState,
    onClick: () -> Unit,
) {
    val colors = uiState.style.toButtonColors()

    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.container,
            contentColor = colors.content,
        ),
    ) {
        Text(
            text = uiState.title.getString(),
            style = AppTheme.typography.subtitle,
        )
    }
}

@Composable
private fun DriverContactActionStyle.toButtonColors(): DriverContactButtonColors =
    when (this) {
        DriverContactActionStyle.PHONE -> DriverContactButtonColors(
            container = AppTheme.colors.background.gray64,
            content = AppTheme.colors.text.white,
        )

        DriverContactActionStyle.WHATSAPP -> DriverContactButtonColors(
            container = AppTheme.colors.background.success,
            content = AppTheme.colors.text.white,
        )

        DriverContactActionStyle.TELEGRAM -> DriverContactButtonColors(
            container = telegramColor,
            content = AppTheme.colors.text.white,
        )
    }

private data class DriverContactButtonColors(
    val container: Color,
    val content: Color,
)
