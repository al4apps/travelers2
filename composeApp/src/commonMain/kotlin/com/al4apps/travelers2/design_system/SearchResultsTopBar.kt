package com.al4apps.travelers2.design_system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.ic_arrow_back

data class SearchResultsTopBarUiState(
    val routeTitle: String,
    val subtitle: String,
    val backIcon: DrawableResource = Res.drawable.ic_arrow_back,
)

@Composable
fun SearchResultsTopBar(
    state: SearchResultsTopBarUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Icon(
            painter = painterResource(state.backIcon),
            contentDescription = null,
            tint = AppTheme.colors.icon.black,
            modifier = Modifier
                .size(20.dp)
                .clickable(onClick = onBackClick),
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = state.routeTitle,
                style = state.routeTitleTextStyle(),
                color = AppTheme.colors.text.default,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = state.subtitle,
                style = AppTheme.typography.body2,
                color = AppTheme.colors.text.tertiary,
            )
        }
    }
}

@Composable
private fun SearchResultsTopBarUiState.routeTitleTextStyle() =
    when {
        routeTitle.length > 38 -> AppTheme.typography.caption
        routeTitle.length > 32 -> AppTheme.typography.body2
        else -> AppTheme.typography.subtitle
    }

@Preview
@Composable
private fun SearchResultsTopBarPreview() {
    SearchResultsTopBar(
        state = SearchResultsTopBarUiState(
            routeTitle = "Исфара (Калача) -- Худжанд (Панчшанбе)",
            subtitle = "15.04.2026, 1 пассажир",
        ),
        onBackClick = {},
    )
}
