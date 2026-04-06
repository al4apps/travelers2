package com.al4apps.travelers2.design_system.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.profile
import com.al4apps.travelers2.shared_model.ChatAvatarUiState
import com.al4apps.travelers2.shared_model.ChatListItemUiState
import com.al4apps.travelers2.shared_model.ChatType
import com.al4apps.travelers2.theme.AppTheme

private val ChatListItemHeight = 72.dp
private val ChatAvatarSize = 48.dp

@Composable
fun ChatListItem(
    state: ChatListItemUiState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(ChatListItemHeight)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        ChatAvatar(
            avatar = state.avatar,
            modifier = Modifier.size(ChatAvatarSize)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp)
        ) {
            Text(
                text = state.title,
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.primary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(state.type.titleRes),
                style = AppTheme.typography.caption,
                color = AppTheme.colors.text.secondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            if (!state.lastMessage.isNullOrBlank()) {
                Text(
                    text = state.lastMessage,
                    style = AppTheme.typography.body2,
                    color = AppTheme.colors.text.tertiary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun ChatListItemDivider(
    modifier: Modifier = Modifier
) {
    HorizontalDivider(
        modifier = modifier.fillMaxWidth(),
        color = AppTheme.colors.border.divider,
        thickness = 1.dp
    )
}

@Composable
private fun ChatAvatar(
    avatar: ChatAvatarUiState?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(AppTheme.colors.background.gray),
        contentAlignment = Alignment.Center
    ) {
        when {
            avatar?.image != null -> {
                androidx.compose.material3.Icon(
                    painter = painterResource(avatar.image),
                    contentDescription = null,
                    tint = AppTheme.colors.icon.accent,
                    modifier = Modifier.size(24.dp)
                )
            }

            !avatar?.initials.isNullOrBlank() -> {
                Text(
                    text = avatar.initials.orEmpty(),
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.primary
                )
            }

            else -> {
                androidx.compose.material3.Icon(
                    painter = painterResource(Res.drawable.profile),
                    contentDescription = null,
                    tint = AppTheme.colors.icon.grayDark,
                    modifier = Modifier.size(22.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ChatListItemPreview() {
    Column(modifier = Modifier.fillMaxWidth().background(AppTheme.colors.background.default)) {
        ChatListItem(
            state = ChatListItemUiState.SAMPLE.copy(
                avatar = ChatAvatarUiState(
                    initials = "ВД"
                )
            )
        )
        ChatListItemDivider()
        ChatListItem(
            state = ChatListItemUiState(
                title = "[Intabia-Expasoft] МП",
                type = ChatType.CHANNEL,
                lastMessage = "Привет! Сегодня проводим встречу?"
            )
        )
    }
}
