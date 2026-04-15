package com.al4apps.travelers2.features.search_results.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.al4apps.travelers2.core.model.TripDateFormatter
import com.al4apps.travelers2.design_system.TripResultCard
import com.al4apps.travelers2.design_system.SearchResultsTopBar
import com.al4apps.travelers2.design_system.SearchResultsTopBarUiState
import com.al4apps.travelers2.design_system.dock.DockWidget
import com.al4apps.travelers2.features.search.model.SearchQuery
import com.al4apps.travelers2.features.search_results.model.SearchResultTripUiModel
import com.al4apps.travelers2.features.search_results.PreviewSearchResultsComponent
import com.al4apps.travelers2.features.search_results.SearchResultsComponent
import com.al4apps.travelers2.shared_model.DockUiState
import com.al4apps.travelers2.shared_model.DockTextButtonUiState
import org.jetbrains.compose.resources.stringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.search_results_passenger_one
import travelers2.composeapp.generated.resources.search_results_tab_all
import travelers2.composeapp.generated.resources.search_results_tab_buses
import travelers2.composeapp.generated.resources.search_results_tab_cars

@Composable
fun SearchResultsScreen(
    component: SearchResultsComponent,
    modifier: Modifier = Modifier,
) {
    var selectedTabId by remember { mutableLongStateOf(Tab.ALL.id) }

    val from = component.query.fromAddress.toRoutePoint()
    val to = component.query.toAddress.toRoutePoint()
    val routeTitle = "$from -- $to"
    val date = component.query.tripDate?.let(TripDateFormatter::toDisplayString).orEmpty()
    val passengers = stringResource(Res.string.search_results_passenger_one)
    val subtitle = if (date.isEmpty()) {
        passengers
    } else {
        "$date, $passengers"
    }
    val tabStates = listOf(
        DockTextButtonUiState(
            id = Tab.ALL.id,
            title = stringResource(Res.string.search_results_tab_all),
            isSelected = selectedTabId == Tab.ALL.id,
        ),
        DockTextButtonUiState(
            id = Tab.CARS.id,
            title = stringResource(Res.string.search_results_tab_cars),
            isSelected = selectedTabId == Tab.CARS.id,
        ),
        DockTextButtonUiState(
            id = Tab.BUSES.id,
            title = stringResource(Res.string.search_results_tab_buses),
            isSelected = selectedTabId == Tab.BUSES.id,
        ),
    )
    val dockState = DockUiState(items = tabStates)
    val tripCards = remember(component.query) {
        buildMockTripCards(component.query)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
    ) {
        SearchResultsTopBar(
            state = SearchResultsTopBarUiState(
                routeTitle = routeTitle,
                subtitle = subtitle,
            ),
            onBackClick = component::onBackClick,
        )
        DockWidget(
            state = dockState,
            isItemsFillWidth = true,
            onItemClick = { id -> selectedTabId = id },
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
                items(
                    items = tripCards,
                    key = { item -> item.id },
                ) { item ->
                    TripResultCard(model = item)
                }
            }
        }
    }
}

private enum class Tab(val id: Long) {
    ALL(1L),
    CARS(2L),
    BUSES(3L),
}

private fun com.al4apps.travelers2.core.model.TripAddress?.toRoutePoint(): String {
    val town = this?.town.orEmpty()
    val locality = this?.locality?.takeIf { it.isNotBlank() } ?: return town
    return "$town ($locality)"
}

private fun buildMockTripCards(query: SearchQuery): List<SearchResultTripUiModel> {
    val from = query.fromAddress.toRoutePoint().ifBlank { "Исфара" }
    val to = query.toAddress.toRoutePoint().ifBlank { "Худжанд" }

    return listOf(
        SearchResultTripUiModel(
            id = "trip_1",
            departureTime = "09:00",
            durationText = "3 ч",
            arrivalTime = "12:00",
            fromAddress = from,
            toAddress = to,
            driverName = "Искандар",
            priceText = "200 с.",
        ),
        SearchResultTripUiModel(
            id = "trip_2",
            departureTime = "11:30",
            durationText = "3 ч 15 м",
            arrivalTime = "14:45",
            fromAddress = from,
            toAddress = to,
            driverName = "Фируз",
            priceText = "220 с.",
        ),
        SearchResultTripUiModel(
            id = "trip_3",
            departureTime = "14:10",
            durationText = "2 ч 50 м",
            arrivalTime = "17:00",
            fromAddress = from,
            toAddress = to,
            driverName = "Рустам",
            priceText = "190 с.",
        ),
    )
}

@Preview
@Composable
private fun SearchResultsScreenPreview() {
    SearchResultsScreen(component = PreviewSearchResultsComponent())
}
