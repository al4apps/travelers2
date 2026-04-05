package com.al4apps.travelers2.design_system.dock

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import ru.intabia.platform.shared_model.DockItem
import ru.intabia.platform.shared_model.DockItemUiState
import ru.intabia.platform.theme.AppTheme

@Composable
fun DockBar(
    items: List<DockItemUiState>,
    modifier: Modifier = Modifier,
    onItemClick: (DockItem) -> Unit
) {
    Card(
        modifier = modifier.padding(horizontal = 16.dp).fillMaxWidth(),
        shape = RoundedCornerShape(40.dp),
        colors = CardDefaults.cardColors(
            containerColor = AppTheme.colors.background.gray70
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            items.forEach { item ->
                DockBarItem(
                    state = item,
                    onClick = { onItemClick(item.item) }
                )
            }
        }
    }
}

@Composable
private fun DockBarItem(
    state: DockItemUiState,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(32.dp)
    val background = if (state.isSelected) {
        AppTheme.colors.background.default
    } else {
        Color.Transparent
    }
    val tint = if (state.isEnabled) {
        AppTheme.colors.icon.accent
    } else {
        AppTheme.colors.icon.grayDark
    }
    Box(
        modifier = modifier
            .height(40.dp)
            .width(60.dp)
            .clip(shape)
            .background(background, shape)
            .clickable(enabled = state.isEnabled, onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(state.item.iconRes),
            contentDescription = stringResource(state.item.titleRes),
            tint = tint,
            modifier = Modifier.size(32.dp)
        )
    }
}

@Composable
@Preview
fun DockBarPreview() {
    DockBar(
        items = listOf(
            DockItemUiState(item = DockItem.CHATS, isSelected = false),
            DockItemUiState(item = DockItem.CONTACTS, isSelected = true),
            DockItemUiState(item = DockItem.SETTINGS, isSelected = false)
        ),
        onItemClick = {}
    )
}
