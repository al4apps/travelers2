package com.al4apps.travelers2.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import kotlinx.coroutines.flow.distinctUntilChanged

private val ITEM_HEIGHT: Dp = 44.dp
private const val VISIBLE_ITEMS = 3

@Composable
fun TimeWheelPicker(
    initialHour: Int,
    initialMinute: Int,
    onValueChange: (hour: Int, minute: Int) -> Unit,
    modifier: Modifier = Modifier,
    minuteStep: Int = 5,
) {
    val hourValues = remember { (0..23).toList() }
    val minuteValues = remember(minuteStep) {
        (0..59 step minuteStep).toList()
    }

    val initialHourIndex = remember(initialHour, hourValues) {
        hourValues.indexOf(initialHour).coerceAtLeast(0)
    }
    val initialMinuteIndex = remember(initialMinute, minuteValues) {
        nearestIndex(minuteValues, initialMinute).coerceAtLeast(0)
    }

    val hourListState = rememberLazyListState(initialFirstVisibleItemIndex = initialHourIndex)
    val minuteListState = rememberLazyListState(initialFirstVisibleItemIndex = initialMinuteIndex)

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(ITEM_HEIGHT * VISIBLE_ITEMS),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Wheel(
            values = hourValues,
            listState = hourListState,
            onValueSelected = { newHour ->
                val currentMinute = minuteValues.getOrNull(minuteListState.firstVisibleItemIndex)
                    ?: minuteValues.first()
                onValueChange(newHour, currentMinute)
            },
            modifier = Modifier.weight(1f),
        )
        Text(
            text = ":",
            style = AppTheme.typography.h2,
            color = AppTheme.colors.text.default,
        )
        Wheel(
            values = minuteValues,
            listState = minuteListState,
            onValueSelected = { newMinute ->
                val currentHour = hourValues.getOrNull(hourListState.firstVisibleItemIndex)
                    ?: hourValues.first()
                onValueChange(currentHour, newMinute)
            },
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun Wheel(
    values: List<Int>,
    listState: LazyListState,
    onValueSelected: (Int) -> Unit,
    modifier: Modifier,
) {
    val density = LocalDensity.current
    val itemHeightPx = remember(density) { with(density) { ITEM_HEIGHT.toPx() } }

    // Snap-to-center on scroll end: when scrolling stops, animate the closest item to the center slot.
    LaunchedEffect(listState, itemHeightPx) {
        snapshotFlow { listState.isScrollInProgress }
            .distinctUntilChanged()
            .collect { scrolling ->
                if (!scrolling) {
                    val firstVisible = listState.firstVisibleItemIndex
                    val offset = listState.firstVisibleItemScrollOffset.toFloat()
                    val targetIndex = if (offset > itemHeightPx / 2f) {
                        (firstVisible + 1).coerceAtMost(values.lastIndex)
                    } else {
                        firstVisible
                    }
                    if (targetIndex != firstVisible || offset != 0f) {
                        listState.animateScrollToItem(targetIndex)
                    }
                    val value = values.getOrNull(targetIndex) ?: return@collect
                    onValueSelected(value)
                }
            }
    }

    val firstVisible = listState.firstVisibleItemIndex
    val offset = listState.firstVisibleItemScrollOffset.toFloat()
    val displayIndex = if (offset > itemHeightPx / 2f) {
        (firstVisible + 1).coerceAtMost(values.lastIndex)
    } else {
        firstVisible
    }
    val centeredValue = values.getOrNull(displayIndex) ?: values.first()

    Box(
        modifier = modifier.height(ITEM_HEIGHT * VISIBLE_ITEMS),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(ITEM_HEIGHT)
                .clip(RoundedCornerShape(12.dp))
                .background(AppTheme.colors.background.gray),
        )
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(ITEM_HEIGHT * VISIBLE_ITEMS),
            contentPadding = PaddingValues(vertical = ITEM_HEIGHT * (VISIBLE_ITEMS / 2)),
        ) {
            wheelItems(values = values) { value ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 0.dp, height = ITEM_HEIGHT),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        text = value.toString().padStart(2, '0'),
                        style = AppTheme.typography.h2,
                        color = if (value == centeredValue) {
                            AppTheme.colors.text.default
                        } else {
                            AppTheme.colors.text.tertiary
                        },
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}

private inline fun androidx.compose.foundation.lazy.LazyListScope.wheelItems(
    values: List<Int>,
    crossinline itemContent: @Composable (Int) -> Unit,
) {
    items(count = values.size) { index ->
        itemContent(values[index])
    }
}

private fun nearestIndex(values: List<Int>, target: Int): Int {
    var bestIndex = 0
    var bestDelta = Int.MAX_VALUE
    values.forEachIndexed { index, value ->
        val delta = kotlin.math.abs(value - target)
        if (delta < bestDelta) {
            bestDelta = delta
            bestIndex = index
        }
    }
    return bestIndex
}
