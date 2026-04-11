package com.al4apps.travelers2.features.search_trips

import com.al4apps.travelers2.features.search_trips.address_search.AddressSearchComponent

sealed interface SearchTripsChild {

    data object Form : SearchTripsChild

    data class AddressSearch(
        val component: AddressSearchComponent,
    ) : SearchTripsChild
}
