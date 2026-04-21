package com.al4apps.travelers2.features.search_results

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.features.search.model.SearchQuery
import com.al4apps.travelers2.features.search_results.map.SearchResultsUiAssembler
import com.al4apps.travelers2.features.search_results.model.SearchResultTripVmModel
import com.al4apps.travelers2.features.search_results.model.SearchResultsUiState
import com.al4apps.travelers2.features.search_results.model.SearchResultsVmState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewSearchResultsComponent : SearchResultsComponent {
    private val query = SearchQuery(
        fromAddress = TripAddress(
            country = "Таджикистан",
            region = "Душанбе",
            town = "Душанбе",
            locality = null,
        ),
        toAddress = TripAddress(
            country = "Таджикистан",
            region = "Согдийская область",
            town = "Худжанд",
            locality = null,
        ),
        tripDate = TripDate(year = 2026, month = 4, day = 15),
    )

    private val vmState = SearchResultsVmState(
        query = query,
        trips = listOf(
            SearchResultTripVmModel(
                id = "trip_preview_1",
                departureTime = "09:00",
                durationText = "3 ч",
                arrivalTime = "12:00",
                fromAddress = "Исфара",
                toAddress = "Худжанд",
                driverName = "Искандар",
                priceText = "200 с.",
            ),
        ),
    )

    override val uiState: Value<SearchResultsUiState> =
        MutableValue(SearchResultsUiAssembler().assemble(vmState))

    override fun onBackClick() = Unit

    override fun onTabClick(tabId: Long) = Unit

    override fun onTripClick(tripId: String) = Unit
}
