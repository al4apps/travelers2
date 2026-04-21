package com.al4apps.travelers2.design_system

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.core.ui.getString
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import com.al4apps.travelers2.theme.AppTheme
import org.jetbrains.compose.resources.DrawableResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.ic_arrow_back

data class SearchResultsTopBarUiState(
    val routeTitle: UiText,
    val subtitle: UiText,
    val backIcon: DrawableResource = Res.drawable.ic_arrow_back,
)

@Composable
fun SearchResultsTopBar(
    state: SearchResultsTopBarUiState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
) {

    HomeTopBar(
        state = HomeTopBarUiState(
            title = state.routeTitle,
            isBackVisible = true,
            isBackClickable = true,
        ),
        modifier = modifier,
        onBackClick = onBackClick,
        titleBlock = {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    text = state.routeTitle.getString(),
                    style = state.routeTitleTextStyle(),
                    color = AppTheme.colors.text.default,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = state.subtitle.getString(),
                    style = AppTheme.typography.body2,
                    color = AppTheme.colors.text.tertiary,
                )
            }
        },
    )
}

@Composable
private fun SearchResultsTopBarUiState.routeTitleTextStyle() =
    when {
        routeTitle.getString().length > 38 -> AppTheme.typography.body2
        routeTitle.getString().length > 32 -> AppTheme.typography.body1
        else -> AppTheme.typography.subtitle
    }

@Preview
@Composable
private fun SearchResultsTopBarPreview() {
    SearchResultsTopBar(
        state = SearchResultsTopBarUiState(
            routeTitle = UiText.Plain("Исфара (Калача) -> Худжанд (Панчшанбе)"),
            subtitle = UiText.Plain("15.04.2026, 1 пассажир"),
        ),
        onBackClick = {},
    )
}
