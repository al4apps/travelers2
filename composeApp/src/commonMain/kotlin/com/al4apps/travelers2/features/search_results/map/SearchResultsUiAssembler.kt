package com.al4apps.travelers2.features.search_results.map

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDateFormatter
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.SearchResultsTopBarUiState
import com.al4apps.travelers2.features.search_results.model.SearchResultTripUiModel
import com.al4apps.travelers2.features.search_results.model.SearchResultsTab
import com.al4apps.travelers2.features.search_results.model.SearchResultsUiState
import com.al4apps.travelers2.features.search_results.model.SearchResultsVmState
import com.al4apps.travelers2.shared_model.DockTextButtonUiState
import com.al4apps.travelers2.shared_model.DockUiState

class SearchResultsUiAssembler {

    fun assemble(vmState: SearchResultsVmState): SearchResultsUiState {
        val from = vmState.query.fromAddress.toRoutePoint()
        val to = vmState.query.toAddress.toRoutePoint()
        val routeTitle = "$from -> $to"
        val date = vmState.query.tripDate?.let(TripDateFormatter::toDisplayString).orEmpty()
        val passengers = vmState.passengerCount.toPassengerLabel()
        val subtitle = if (date.isEmpty()) {
            passengers
        } else {
            "$date, $passengers"
        }

        val tabs = DockUiState(
            items = listOf(
                DockTextButtonUiState(
                    id = SearchResultsTab.ALL.id,
                    title = TAB_ALL,
                    isSelected = vmState.selectedTab == SearchResultsTab.ALL,
                ),
                DockTextButtonUiState(
                    id = SearchResultsTab.CARS.id,
                    title = TAB_CARS,
                    isSelected = vmState.selectedTab == SearchResultsTab.CARS,
                ),
                DockTextButtonUiState(
                    id = SearchResultsTab.BUSES.id,
                    title = TAB_BUSES,
                    isSelected = vmState.selectedTab == SearchResultsTab.BUSES,
                ),
            ),
        )

        val cards = vmState.trips.map { trip ->
            SearchResultTripUiModel(
                id = trip.id,
                departureTime = trip.departureTime,
                durationText = trip.durationText,
                arrivalTime = trip.arrivalTime,
                fromAddress = trip.fromAddress,
                toAddress = trip.toAddress,
                driverName = trip.driverName,
                priceText = trip.priceText,
            )
        }

        return SearchResultsUiState(
            topBar = SearchResultsTopBarUiState(
                routeTitle = UiText.Plain(routeTitle),
                subtitle = UiText.Plain(subtitle),
            ),
            tabs = tabs,
            tripCards = cards,
        )
    }
}

private fun TripAddress?.toRoutePoint(): String {
    val town = this?.town.orEmpty()
    val locality = this?.locality?.takeIf { it.isNotBlank() } ?: return town
    return "$town ($locality)"
}

private fun Int.toPassengerLabel(): String =
    when (this) {
        1 -> "1 пассажир"
        in 2..4 -> "$this пассажира"
        else -> "$this пассажиров"
    }

private const val TAB_ALL = "Все"
private const val TAB_CARS = "Легковые"
private const val TAB_BUSES = "Автобусы"
