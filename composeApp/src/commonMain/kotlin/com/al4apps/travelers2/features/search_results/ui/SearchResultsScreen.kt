package com.al4apps.travelers2.features.search_results.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.design_system.SearchResultsTopBar
import com.al4apps.travelers2.design_system.TripResultCard
import com.al4apps.travelers2.design_system.dock.DockWidget
import com.al4apps.travelers2.features.search_results.PreviewSearchResultsComponent
import com.al4apps.travelers2.features.search_results.SearchResultsComponent
import com.al4apps.travelers2.features.search_results.model.SearchResultTripUiModel
import com.arkivanov.decompose.extensions.compose.subscribeAsState

@Composable
fun SearchResultsScreen(
    component: SearchResultsComponent,
    bottomSpacer: @Composable () -> Unit = {},
) {
    val uiState by component.uiState.subscribeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .safeDrawingPadding(),
    ) {
        SearchResultsTopBar(
            state = uiState.topBar,
            onBackClick = component::onBackClick,
        )
        DockWidget(
            state = uiState.tabs,
            isItemsFillWidth = true,
            onItemClick = component::onTabClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                itemsIndexed(
                    items = uiState.tripCards,
                    key = { _: Int, item: SearchResultTripUiModel -> item.id },
                ) { i: Int, item: SearchResultTripUiModel ->
                    TripResultCard(
                        model = item,
                        onClick = { component.onTripClick(item.id) },
                    )
                    if (i == uiState.tripCards.lastIndex) bottomSpacer()
                }
            }
        }
    }
}

@Preview
@Composable
private fun SearchResultsScreenPreview() {
    SearchResultsScreen(component = PreviewSearchResultsComponent())
}
