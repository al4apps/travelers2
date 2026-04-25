package com.al4apps.travelers2.design_system.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.al4apps.travelers2.core.dialog.BaseContentDialogComponent
import com.al4apps.travelers2.core.dialog.DialogAlign
import com.al4apps.travelers2.core.dialog.DialogBackground
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.button.PrimaryButton
import com.al4apps.travelers2.design_system.button.SecondaryButton
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun TravelersBaseContentDialog(
    component: BaseContentDialogComponent,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit = {},
) {
    val dialogContentState = component.dialogContentState.subscribeAsState().value
    val uiState = component.uiState.subscribeAsState().value

    Dialog(
        onDismissRequest = {
            if (dialogContentState.isDismissAllow) {
                component.onDismissRequest()
            }
        },
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            dismissOnBackPress = dialogContentState.isDismissAllow,
            dismissOnClickOutside = false,
        ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(dialogContentState.background.toScrimColor())
        ) {
            if (dialogContentState.isDismissAllow) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = component::onDismissRequest,
                        ),
                )
            }

            Surface(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 24.dp)
                    .align(dialogContentState.align.toAlignment()),
                shape = RoundedCornerShape(24.dp),
                color = AppTheme.colors.background.default,
                tonalElevation = 0.dp,
                shadowElevation = 12.dp,
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp, vertical = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        Text(
                            text = uiState.title.getString(),
                            style = AppTheme.typography.h2,
                            color = AppTheme.colors.text.default,
                        )
                        Text(
                            text = uiState.text.getString(),
                            style = AppTheme.typography.body1,
                            color = AppTheme.colors.text.tertiary,
                        )
                    }

                    content()

                    uiState.primaryButton?.let { primaryButton ->
                        PrimaryButton(
                            uiState = ButtonUiState(
                                text = primaryButton.text,
                                enabled = primaryButton.enabled,
                            ),
                            modifier = Modifier.fillMaxWidth(),
                            onClick = component::onPrimaryClick,
                        )
                    }

                    uiState.secondaryButton?.let { secondaryButton ->
                        SecondaryButton(
                            text = secondaryButton.text.getString(),
                            enabled = secondaryButton.enabled,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = component::onSecondaryClick,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DialogBackground.toScrimColor(): Color =
    when (this) {
        DialogBackground.DIMMED -> AppTheme.colors.background.blackLight
        DialogBackground.TRANSPARENT -> Color.Transparent
    }

private fun DialogAlign.toAlignment(): Alignment =
    when (this) {
        DialogAlign.TOP -> Alignment.TopCenter
        DialogAlign.CENTER -> Alignment.Center
        DialogAlign.BOTTOM -> Alignment.BottomCenter
    }
