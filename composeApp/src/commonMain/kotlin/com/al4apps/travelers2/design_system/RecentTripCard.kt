package com.al4apps.travelers2.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.design_system.button.SecondaryButton
import com.al4apps.travelers2.theme.AppTheme

data class RecentTripCardState(
    val id: String,
    val fromCity: String,
    val toCity: String,
    val dateText: String,
    val statusLabel: UiText?,
    val isOnModeration: Boolean,
)

@Composable
fun RecentTripCard(
    state: RecentTripCardState,
    onRepeatClick: () -> Unit,
    repeatLabel: String,
    modifier: Modifier = Modifier,
) {
    val containerColor = if (state.isOnModeration) {
        AppTheme.colors.background.secondary
    } else {
        AppTheme.colors.background.default
    }
    ElevatedCard(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            RouteDots()
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = state.fromCity,
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colors.text.default,
                )
                Text(
                    text = state.toCity,
                    style = AppTheme.typography.subtitle,
                    color = AppTheme.colors.text.default,
                )
                Text(
                    text = state.dateText,
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.tertiary,
                )
                state.statusLabel?.let { label ->
                    Text(
                        text = label.getString(),
                        style = AppTheme.typography.caption,
                        color = if (state.isOnModeration) {
                            AppTheme.colors.text.accent
                        } else {
                            AppTheme.colors.text.secondary
                        },
                    )
                }
            }
            SecondaryButton(
                text = repeatLabel,
                enabled = true,
                onClick = onRepeatClick,
            )
        }
    }
}

@Composable
private fun RouteDots() {
    Column(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(AppTheme.colors.border.secondary),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .clip(CircleShape)
                    .background(AppTheme.colors.background.default),
            )
        }
        Box(
            modifier = Modifier
                .size(width = 1.dp, height = 16.dp)
                .background(AppTheme.colors.border.divider),
        )
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(AppTheme.colors.border.secondary),
        )
    }
}
