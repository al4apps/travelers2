package com.al4apps.travelers2.features.create_trip.route

import com.al4apps.travelers2.features.search_form.address_search.AddressSearchComponent
import com.al4apps.travelers2.features.search_form.calendar.CalendarComponent
import kotlinx.serialization.Serializable

@Serializable
sealed interface RouteSubConfig {
    @Serializable
    data object Form : RouteSubConfig

    @Serializable
    data class AddressSearch(val field: RouteAddressField) : RouteSubConfig

    @Serializable
    data object Calendar : RouteSubConfig
}

sealed interface RouteSubChild {
    data object Form : RouteSubChild

    data class AddressSearch(val component: AddressSearchComponent) : RouteSubChild

    data class Calendar(val component: CalendarComponent) : RouteSubChild
}
