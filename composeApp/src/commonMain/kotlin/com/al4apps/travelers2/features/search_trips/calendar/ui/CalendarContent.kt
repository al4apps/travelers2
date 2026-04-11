package com.al4apps.travelers2.features.search_trips.calendar.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.asString
import com.al4apps.travelers2.features.search_trips.calendar.CalendarComponent
import com.al4apps.travelers2.features.search_trips.calendar.PreviewCalendarComponent
import com.al4apps.travelers2.features.search_trips.calendar.model.CalendarDayUiState
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.extensions.compose.subscribeAsState
import org.jetbrains.compose.resources.painterResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.close

@Composable
fun CalendarContent(
    component: CalendarComponent,
    modifier: Modifier = Modifier,
) {
    val uiState by component.uiState.subscribeAsState()
    var totalDragDelta by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
        ) {
            Icon(
                painter = painterResource(Res.drawable.close),
                modifier = Modifier.clickable(onClick = component::onCloseClick),
                contentDescription = null,
            )
        }

        MonthHeaderRow(
            month = uiState.monthTitle.asString(),
            year = uiState.year.toString(),
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            uiState.weekdays.forEach { day ->
                Text(
                    text = day.asString(),
                    style = AppTheme.typography.caption,
                    color = AppTheme.colors.text.secondary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.weight(1f),
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectHorizontalDragGestures(
                        onDragStart = { totalDragDelta = 0f },
                        onHorizontalDrag = { _, dragAmount ->
                            totalDragDelta += dragAmount
                        },
                        onDragEnd = {
                            when {
                                totalDragDelta > SWIPE_THRESHOLD_PX -> component.onPreviousMonthClick()
                                totalDragDelta < -SWIPE_THRESHOLD_PX -> component.onNextMonthClick()
                            }
                        },
                    )
                },
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            repeat(6) { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    repeat(7) { column ->
                        val cell = uiState.days[(row * 7) + column]
                        DayCell(
                            cell = cell,
                            onClick = component::onDayClick,
                            modifier = Modifier.weight(1f),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MonthHeaderRow(
    month: String,
    year: String,
) {
    Box(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = month,
            style = AppTheme.typography.h2,
            color = AppTheme.colors.text.default,
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
        )
        Row(
            modifier = Modifier.align(Alignment.CenterEnd),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = year,
                style = AppTheme.typography.body2,
                color = AppTheme.colors.text.secondary,
            )
        }
    }
}

@Composable
private fun DayCell(
    cell: CalendarDayUiState,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val borderStroke = if (cell.isSelected) {
        BorderStroke(width = 2.dp, color = AppTheme.colors.border.secondary)
    } else {
        BorderStroke(width = 0.dp, color = Color.Transparent)
    }

    Box(
        modifier = modifier
            .aspectRatio(1f)
            .clip(CircleShape)
            .then(
                if (cell.day != null && cell.isEnabled) {
                    Modifier.clickable { onClick(cell.day) }
                } else {
                    Modifier
                }
            ),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .border(borderStroke, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = cell.day?.toString().orEmpty(),
                style = AppTheme.typography.body1,
                color = when {
                    cell.day == null -> Color.Transparent
                    cell.isEnabled -> AppTheme.colors.text.default
                    else -> AppTheme.colors.text.tertiary
                },
            )
        }
    }
}

private const val SWIPE_THRESHOLD_PX = 160f

@Composable
@androidx.compose.ui.tooling.preview.Preview
private fun CalendarContentPreview() {
    CalendarContent(component = PreviewCalendarComponent())
}
