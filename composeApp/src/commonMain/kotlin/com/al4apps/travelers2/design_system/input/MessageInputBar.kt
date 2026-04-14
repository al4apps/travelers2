package com.al4apps.travelers2.design_system.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.getString
import org.jetbrains.compose.resources.vectorResource
import com.al4apps.travelers2.shared_model.MessageInputActionType
import com.al4apps.travelers2.shared_model.MessageInputBarUiState
import com.al4apps.travelers2.theme.AppTheme

private val MessageInputMinHeight = 56.dp
private val MessageInputMaxHeight = 156.dp

@Composable
fun MessageInputBar(
    state: MessageInputBarUiState,
    onTextChange: (String) -> Unit,
    onEmojiClick: () -> Unit,
    onSendClick: () -> Unit,
    onAttachmentClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.gray70
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = MessageInputMinHeight, max = MessageInputMaxHeight)
                .padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MessageInputIcon(
                onClick = onEmojiClick
            ) {
                Icon(
                    imageVector = vectorResource(state.leadingIcon),
                    contentDescription = null,
                    tint = AppTheme.colors.icon.accent,
                    modifier = Modifier.size(22.dp)
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(20.dp))
                    .background(AppTheme.colors.background.default)
                    .padding(horizontal = 14.dp, vertical = 10.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                if (state.text.isBlank()) {
                    Text(
                        text = state.placeholder.getString(),
                        style = AppTheme.typography.body1,
                        color = AppTheme.colors.text.tertiary
                    )
                }

                BasicTextField(
                    value = state.text,
                    onValueChange = onTextChange,
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 6,
                    textStyle = AppTheme.typography.body1.copy(
                        color = AppTheme.colors.text.primary
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Default
                    ),
                )
            }

            MessageInputIcon(
                onClick = {
                    when (state.actionType) {
                        MessageInputActionType.ATTACHMENT -> onAttachmentClick()
                        MessageInputActionType.SEND -> onSendClick()
                    }
                }
            ) {
                Icon(
                    imageVector = vectorResource(state.actionIcon),
                    contentDescription = null,
                    tint = AppTheme.colors.icon.accent,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

@Composable
private fun MessageInputIcon(
    onClick: () -> Unit,
    content: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}
