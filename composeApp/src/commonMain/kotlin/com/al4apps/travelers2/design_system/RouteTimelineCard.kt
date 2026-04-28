package com.al4apps.travelers2.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme

data class RouteTimelinePoint(
    val time: String?,
    val title: String,
    val subtitle: String?,
)

@Composable
fun RouteTimelineCard(
    departure: RouteTimelinePoint,
    arrival: RouteTimelinePoint,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        TimelineRow(point = departure, isStart = true)
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Box(modifier = Modifier.width(56.dp), contentAlignment = Alignment.Center) {
                Box(
                    modifier = Modifier
                        .size(width = 1.dp, height = 18.dp)
                        .background(AppTheme.colors.border.divider),
                )
            }
        }
        TimelineRow(point = arrival, isStart = false)
    }
}

@Composable
private fun TimelineRow(point: RouteTimelinePoint, isStart: Boolean) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier.width(56.dp),
            contentAlignment = Alignment.Center,
        ) {
            if (isStart) {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.border.secondary),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .size(5.dp)
                            .clip(CircleShape)
                            .background(AppTheme.colors.background.default),
                    )
                }
            } else {
                Box(
                    modifier = Modifier
                        .size(12.dp)
                        .clip(CircleShape)
                        .background(AppTheme.colors.border.secondary),
                )
            }
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = point.title,
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.default,
            )
            if (point.subtitle != null) {
                Text(
                    text = point.subtitle,
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.tertiary,
                )
            }
        }
        if (point.time != null) {
            Text(
                text = point.time,
                style = AppTheme.typography.subtitle,
                color = AppTheme.colors.text.secondary,
            )
        }
    }
}
