package com.al4apps.travelers2.design_system.dock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.kmp_wizard.shared_models.DockButtonUiState
import com.al4apps.kmp_wizard.shared_models.DockIconTextButtonUiState
import com.al4apps.kmp_wizard.shared_models.DockTextButtonUiState
import com.al4apps.kmp_wizard.shared_models.DockUiState
import intabiaplatform.composeapp.generated.resources.Res
import intabiaplatform.composeapp.generated.resources.ic_favorite_selected
import intabiaplatform.composeapp.generated.resources.ic_play
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import ru.intabia.platform.theme.AppTheme

@Composable
fun DockWidget(
    state: DockUiState,
    modifier: Modifier = Modifier,
    onItemClick: (Long) -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(28.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.gray70
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            state.items.forEach { item ->
                when (item) {
                    is DockIconTextButtonUiState -> {
                        DockIconTextButton(
                            state = item,
                            onClick = { onItemClick(item.id) }
                        )
                    }

                    is DockTextButtonUiState -> {
                        DockTextButton(
                            state = item,
                            onClick = { onItemClick(item.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DockIconTextButton(
    state: DockIconTextButtonUiState,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(16.dp)
    val background = if (state.isSelected) {
        AppTheme.colors.background.primary
    } else {
        AppTheme.colors.background.default
    }
    Column(
        modifier = Modifier
            .widthIn(min = 56.dp)
            .clip(shape)
            .background(background, shape)
            .clickable(enabled = state.isEnabled, onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DockIcon(state.icon)
        if (state.title.isNotBlank()) {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = state.title,
                style = AppTheme.typography.caption,
                color = if (state.isEnabled) {
                    AppTheme.colors.text.primary
                } else {
                    AppTheme.colors.text.disabledSecondary
                }
            )
        }
    }
}

@Composable
fun DockTextButton(
    state: DockTextButtonUiState,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(18.dp)
    val background = if (state.isSelected) {
        AppTheme.colors.button.primary
    } else {
        Color.Unspecified
    }
    val textColor = when {
        !state.isEnabled -> AppTheme.colors.text.disabledSecondary
        state.isDestructive -> AppTheme.colors.icon.error
        state.isSelected -> AppTheme.colors.button.primaryTextColor
        else -> AppTheme.colors.text.primary
    }
    Text(
        text = state.title,
        style = AppTheme.typography.subtitle,
        color = textColor,
        modifier = Modifier
            .clip(shape)
            .background(background, shape)
            .clickable(enabled = state.isEnabled, onClick = onClick)
            .padding(horizontal = 20.dp, vertical = 6.dp)
    )
}

@Composable
private fun DockIcon(icon: DrawableResource?) {
    if (icon == null) return
    Icon(
        imageVector = vectorResource(icon),
        contentDescription = null,
        tint = AppTheme.colors.icon.accent,
        modifier = Modifier.size(22.dp)
    )
}

@Composable
@Preview
fun DockWidgetPreview() {
    val sample = DockUiState(
        items = listOf<DockButtonUiState>(
            DockIconTextButtonUiState(
                id = 1L,
                title = "Play",
                icon = Res.drawable.ic_play,
                isSelected = true
            ),
            DockIconTextButtonUiState(
                id = 2L,
                title = "Fav",
                icon = Res.drawable.ic_favorite_selected
            ),
            DockTextButtonUiState(
                id = 3L,
                title = "Settings"
            ),
            DockTextButtonUiState(
                id = 4L,
                title = "Help",
                isEnabled = false
            )
        )
    )
    DockWidget(
        state = sample,
        onItemClick = {}
    )
}
