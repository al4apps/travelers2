package com.al4apps.travelers2.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.asString
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.arrow_left
import travelers2.composeapp.generated.resources.exit
import travelers2.composeapp.generated.resources.search

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    state: HomeTopBarUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onLeadingClick: () -> Unit = {},
    onPrimaryActionClick: () -> Unit = {},
    onSecondaryActionClick: () -> Unit = {},
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        title = {
            Text(
                text = state.title.asString(),
                style = AppTheme.typography.h2,
                color = AppTheme.colors.text.primary
            )
        },
        navigationIcon = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (state.isBackVisible) {
                    HomeTopBarIcon(
                        icon = state.backIcon,
                        isClickable = state.isBackClickable,
                        onClick = onBackClick
                    )
                }
                state.leadingIcon?.let { icon ->
                    HomeTopBarIcon(
                        icon = icon,
                        isClickable = state.isLeadingClickable,
                        onClick = onLeadingClick
                    )
                }
            }
        },
        actions = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                state.actionIconPrimary?.let { icon ->
                    HomeTopBarIcon(
                        icon = icon,
                        isClickable = state.isActionPrimaryClickable,
                        onClick = onPrimaryActionClick
                    )
                }
                state.actionIconSecondary?.let { icon ->
                    HomeTopBarIcon(
                        icon = icon,
                        isClickable = state.isActionSecondaryClickable,
                        onClick = onSecondaryActionClick
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = AppTheme.colors.background.default
        )
    )
}

@Composable
private fun HomeTopBarIcon(
    icon: DrawableResource,
    isClickable: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tint = if (isClickable) {
        AppTheme.colors.icon.black
    } else {
        AppTheme.colors.icon.grayDark
    }
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(CircleShape)
            .clickable(
                enabled = isClickable,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = vectorResource(icon),
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(22.dp)
        )
    }
}

@Preview
@Composable
fun HomeTopBarPreview() {
    HomeTopBar(
        state = HomeTopBarUiState(
            title = UiText.Plain("Чаты"),
            isBackVisible = true,
            backIcon = Res.drawable.arrow_left,
            leadingIcon = Res.drawable.search,
            actionIconPrimary = Res.drawable.exit,
            actionIconSecondary = Res.drawable.search
        )
    )
}
