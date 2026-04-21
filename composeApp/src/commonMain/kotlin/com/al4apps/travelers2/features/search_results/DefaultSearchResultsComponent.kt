package com.al4apps.travelers2.features.search_results

import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.features.search.model.SearchQuery
import com.al4apps.travelers2.features.search_results.map.SearchResultsUiAssembler
import com.al4apps.travelers2.features.search_results.model.SearchResultTripVmModel
import com.al4apps.travelers2.features.search_results.model.SearchResultsTab
import com.al4apps.travelers2.features.search_results.model.SearchResultsUiState
import com.al4apps.travelers2.features.search_results.model.SearchResultsVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value

class DefaultSearchResultsComponent(
    componentContext: ComponentContext,
    query: SearchQuery,
    private val onBack: () -> Unit,
    private val onTripClick: (tripId: String) -> Unit,
    private val uiAssembler: SearchResultsUiAssembler = SearchResultsUiAssembler(),
) : SearchResultsComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = SearchResultsVmState.serializer(),
        ) ?: SearchResultsVmState(
            query = query,
            trips = createMockTrips(query = query),
        ),
    )

    override val uiState: Value<SearchResultsUiState> =
        vmStateStore.toUiState(
            scope = featureScope,
            assemble = uiAssembler::assemble,
        )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = SearchResultsVmState.serializer(),
            supplier = vmStateStore::value,
        )
    }

    override fun onBackClick() {
        onBack.invoke()
    }

    override fun onTabClick(tabId: Long) {
        val selectedTab = SearchResultsTab.entries.firstOrNull { it.id == tabId } ?: return
        vmStateStore.updateState { state ->
            state.copy(selectedTab = selectedTab)
        }
    }

    override fun onTripClick(tripId: String) {
        onTripClick.invoke(tripId)
    }

    private companion object {
        const val STATE_KEY = "search_results_vm_state"
    }
}

private fun createMockTrips(query: SearchQuery): List<SearchResultTripVmModel> {
    val from = query.fromAddress.toRoutePoint().ifBlank { "Исфара" }
    val to = query.toAddress.toRoutePoint().ifBlank { "Худжанд" }

    return listOf(
        SearchResultTripVmModel(
            id = "trip_1",
            departureTime = "09:00",
            durationText = "3 ч",
            arrivalTime = "12:00",
            fromAddress = from,
            toAddress = to,
            driverName = "Искандар",
            priceText = "200 с.",
        ),
        SearchResultTripVmModel(
            id = "trip_2",
            departureTime = "11:30",
            durationText = "3 ч 15 м",
            arrivalTime = "14:45",
            fromAddress = from,
            toAddress = to,
            driverName = "Фируз",
            priceText = "220 с.",
        ),
        SearchResultTripVmModel(
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

private fun TripAddress?.toRoutePoint(): String {
    val town = this?.town.orEmpty()
    val locality = this?.locality?.takeIf { it.isNotBlank() } ?: return town
    return "$town ($locality)"
}
