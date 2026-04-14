package com.al4apps.travelers2.features.search_form.map

import com.al4apps.travelers2.core.model.TripDateFormatter
import com.al4apps.travelers2.core.model.toDisplayString
import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.search_form.model.SearchTripsUiState
import com.al4apps.travelers2.features.search_form.model.SearchTripsVmState
import com.al4apps.travelers2.shared_model.ButtonUiState
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.search_trips_button
import travelers2.composeapp.generated.resources.search_trips_from
import travelers2.composeapp.generated.resources.search_trips_title
import travelers2.composeapp.generated.resources.search_trips_to
import travelers2.composeapp.generated.resources.search_trips_when

class SearchTripsUiAssembler {

    fun assemble(vmState: SearchTripsVmState): SearchTripsUiState {

        val isFormValid = vmState.tripDate != null && vmState.fromAddress != null &&
                vmState.toAddress != null
        val buttonUiState = ButtonUiState(
            text = UiText.Resource(Res.string.search_trips_button),
            enabled = isFormValid
        )
        return SearchTripsUiState(
            title = UiText.Resource(Res.string.search_trips_title),
            fromAddress = vmState.fromAddress?.toDisplayString().orEmpty(),
            toAddress = vmState.toAddress?.toDisplayString().orEmpty(),
            tripDate = vmState.tripDate?.let(TripDateFormatter::toDisplayString).orEmpty(),
            fromPlaceholder = UiText.Resource(Res.string.search_trips_from),
            toPlaceholder = UiText.Resource(Res.string.search_trips_to),
            datePlaceholder = UiText.Resource(Res.string.search_trips_when),
            searchButtonUiState = buttonUiState
        )
    }
}
