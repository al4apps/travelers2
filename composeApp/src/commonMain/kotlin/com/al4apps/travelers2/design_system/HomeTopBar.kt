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
import intabiaplatform.composeapp.generated.resources.Res
import intabiaplatform.composeapp.generated.resources.ic_arrow_back
import intabiaplatform.composeapp.generated.resources.ic_settings
import intabiaplatform.composeapp.generated.resources.ic_visibility_active
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.vectorResource
import ru.intabia.platform.shared_model.HomeTopBarUiState
import ru.intabia.platform.shared_model.getString
import ru.intabia.platform.shared_model.UiText
import ru.intabia.platform.theme.AppTheme

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
                text = state.title.getString(),
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
            title = UiText.Dynamic("Чаты"),
            isBackVisible = true,
            backIcon = Res.drawable.ic_arrow_back,
            leadingIcon = Res.drawable.ic_visibility_active,
            actionIconPrimary = Res.drawable.ic_settings,
            actionIconSecondary = Res.drawable.ic_visibility_active
        )
    )
}
