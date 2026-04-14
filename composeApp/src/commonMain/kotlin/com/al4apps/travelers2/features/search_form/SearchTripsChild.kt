package com.al4apps.travelers2.features.search_form

import com.al4apps.travelers2.features.search_form.address_search.AddressSearchComponent
import com.al4apps.travelers2.features.search_form.calendar.CalendarComponent

sealed interface SearchTripsChild {

    data object Form : SearchTripsChild

    data class Calendar(
        val component: CalendarComponent,
    ) : SearchTripsChild

    data class AddressSearch(
        val component: AddressSearchComponent,
    ) : SearchTripsChild
}
