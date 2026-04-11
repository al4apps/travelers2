package com.al4apps.travelers2.features.search_trips

import kotlinx.serialization.Serializable

@Serializable
sealed interface SearchTripsConfig {

    @Serializable
    data object Form : SearchTripsConfig

    @Serializable
    data object Calendar : SearchTripsConfig

    @Serializable
    data class AddressSearch(
        val field: SearchTripsAddressField,
    ) : SearchTripsConfig
}
