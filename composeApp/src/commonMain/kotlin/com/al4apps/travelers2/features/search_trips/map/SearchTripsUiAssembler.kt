package com.al4apps.travelers2.features.search_trips.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.search_trips.model.SearchTripsUiState
import com.al4apps.travelers2.features.search_trips.model.SearchTripsVmState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.search_trips_button
import travelers2.composeapp.generated.resources.search_trips_from
import travelers2.composeapp.generated.resources.search_trips_title
import travelers2.composeapp.generated.resources.search_trips_to
import travelers2.composeapp.generated.resources.search_trips_when

class SearchTripsUiAssembler {

    fun assemble(vmState: SearchTripsVmState): SearchTripsUiState =
        SearchTripsUiState(
            title = UiText.Resource(Res.string.search_trips_title),
            fromAddress = vmState.fromAddress,
            toAddress = vmState.toAddress,
            tripDate = vmState.tripDate,
            fromPlaceholder = UiText.Resource(Res.string.search_trips_from),
            toPlaceholder = UiText.Resource(Res.string.search_trips_to),
            datePlaceholder = UiText.Resource(Res.string.search_trips_when),
            searchButtonText = UiText.Resource(Res.string.search_trips_button),
        )
}
