package com.al4apps.travelers2.design_system.message

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import intabiaplatform.composeapp.generated.resources.Res
import intabiaplatform.composeapp.generated.resources.ic_check
import intabiaplatform.composeapp.generated.resources.ic_image_not_found
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.vectorResource
import ru.intabia.platform.shared_model.MessageAvatarUiState
import ru.intabia.platform.shared_model.MessageCardUiState
import ru.intabia.platform.shared_model.MessageDirection
import ru.intabia.platform.theme.AppTheme

@Composable
fun MessageCard(
    state: MessageCardUiState,
    modifier: Modifier = Modifier
) {
    val isOutgoing = state.direction == MessageDirection.OUTGOING
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        horizontalArrangement = if (isOutgoing) Arrangement.End else Arrangement.Start,
        verticalAlignment = Alignment.Bottom
    ) {
        if (!isOutgoing) {
            MessageAvatarBlock(
                avatar = state.avatar,
                modifier = Modifier.align(Alignment.Bottom)
            )
            if (state.avatar != null) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
        MessageBubble(
            state = state,
            modifier = Modifier.widthIn(max = 320.dp)
        )
    }
}

@Composable
private fun MessageBubble(
    state: MessageCardUiState,
    modifier: Modifier = Modifier
) {
    val isOutgoing = state.direction == MessageDirection.OUTGOING
    val background = if (isOutgoing) {
        AppTheme.colors.background.primary
    } else {
        AppTheme.colors.background.default
    }
    val shape = RoundedCornerShape(
        topStart = 16.dp,
        topEnd = 16.dp,
        bottomEnd = if (isOutgoing) 0.dp else 16.dp,
        bottomStart = if (isOutgoing) 16.dp else 0.dp
    )
    Column(
        modifier = modifier
            .clip(shape)
            .background(background)
            .padding(horizontal = 12.dp, vertical = 8.dp)
    ) {
        MessageSenderBlock(state.senderName, isOutgoing)
        MessageTextBlock(state.text)
        MessageMetaBlock(
            time = state.time,
            statusIcon = state.statusIcon,
            isOutgoing = isOutgoing
        )
    }
}

@Composable
private fun MessageSenderBlock(
    senderName: String?,
    isOutgoing: Boolean
) {
    if (senderName.isNullOrBlank() || isOutgoing) return
    Text(
        text = senderName,
        style = AppTheme.typography.caption,
        color = AppTheme.colors.text.accent,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.size(4.dp))
}

@Composable
private fun MessageTextBlock(
    text: String
) {
    if (text.isBlank()) return
    Text(
        text = text,
        style = AppTheme.typography.body1,
        color = AppTheme.colors.text.primary
    )
}

@Composable
private fun MessageMetaBlock(
    time: String,
    statusIcon: DrawableResource?,
    isOutgoing: Boolean
) {
    if (time.isBlank() && statusIcon == null) return
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (time.isNotBlank()) {
            Text(
                text = time,
                style = AppTheme.typography.caption,
                color = AppTheme.colors.text.tertiary
            )
        }
        if (statusIcon != null && isOutgoing) {
            Spacer(modifier = Modifier.width(6.dp))
            Icon(
                imageVector = vectorResource(statusIcon),
                contentDescription = null,
                tint = AppTheme.colors.icon.accent,
                modifier = Modifier.size(14.dp)
            )
        }
    }
}

@Composable
private fun MessageAvatarBlock(
    avatar: MessageAvatarUiState?,
    modifier: Modifier = Modifier
) {
    if (avatar == null) return
    val background = AppTheme.colors.background.secondary
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        when {
            avatar.image != null -> {
                Icon(
                    painter = painterResource(avatar.image),
                    contentDescription = null,
                    tint = AppTheme.colors.icon.accent,
                    modifier = Modifier.size(22.dp)
                )
            }
            !avatar.initials.isNullOrBlank() -> {
                Text(
                    text = avatar.initials,
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.primary
                )
            }
            else -> {
                Icon(
                    painter = painterResource(Res.drawable.ic_image_not_found),
                    contentDescription = null,
                    tint = AppTheme.colors.icon.grayDark,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MessageCardPreview() {
    Column(modifier = Modifier.padding(16.dp)) {
        MessageCard(
            state = MessageCardUiState.SAMPLE_INCOMING.copy(
                avatar = MessageAvatarUiState(
                    image = Res.drawable.ic_image_not_found,
                    initials = "ВД"
                )
            )
        )
        Spacer(modifier = Modifier.size(12.dp))
        MessageCard(
            state = MessageCardUiState.SAMPLE_OUTGOING.copy(
                statusIcon = Res.drawable.ic_check
            )
        )
    }
}
