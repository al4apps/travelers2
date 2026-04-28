package com.al4apps.travelers2.design_system

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import kotlinx.coroutines.flow.distinctUntilChanged

@Composable
fun TimeWheelPicker(
    initialHour: Int,
    initialMinute: Int,
    onValueChange: (hour: Int, minute: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(180.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Wheel(
            range = 0..23,
            initialValue = initialHour,
            modifier = Modifier.weight(1f),
            onValueSelected = { onValueChange(it, initialMinute) },
        )
        Text(
            text = ":",
            style = AppTheme.typography.h2,
            color = AppTheme.colors.text.default,
        )
        Wheel(
            range = 0..59,
            initialValue = initialMinute,
            modifier = Modifier.weight(1f),
            onValueSelected = { onValueChange(initialHour, it) },
        )
    }
}

@Composable
private fun Wheel(
    range: IntRange,
    initialValue: Int,
    modifier: Modifier,
    onValueSelected: (Int) -> Unit,
) {
    val itemHeight = 44.dp
    val visibleItems = 3
    val centerIndex = visibleItems / 2

    val values = remember(range) { range.toList() }
    val initialIndex = (initialValue - range.first).coerceIn(0, values.lastIndex)
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = initialIndex)

    val centeredValue: Int =
        values.getOrNull(listState.firstVisibleItemIndex) ?: values.first()

    LaunchedEffect(listState, values) {
        snapshotFlow { listState.firstVisibleItemIndex to listState.isScrollInProgress }
            .distinctUntilChanged()
            .collect { (index, scrolling) ->
                if (!scrolling) {
                    val value = values.getOrNull(index) ?: return@collect
                    onValueSelected(value)
                }
            }
    }

    Box(
        modifier = modifier.height(itemHeight * visibleItems),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight)
                .clip(RoundedCornerShape(12.dp))
                .background(AppTheme.colors.background.gray),
        )
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxWidth()
                .height(itemHeight * visibleItems),
            contentPadding = PaddingValues(vertical = itemHeight * centerIndex),
        ) {
            items(values) { value ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(width = 0.dp, height = itemHeight),
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

private inline fun androidx.compose.foundation.lazy.LazyListScope.items(
    values: List<Int>,
    crossinline itemContent: @Composable (Int) -> Unit,
) {
    items(count = values.size) { index ->
        itemContent(values[index])
    }
}
