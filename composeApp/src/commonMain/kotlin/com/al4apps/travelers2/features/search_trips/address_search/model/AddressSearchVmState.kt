package com.al4apps.travelers2.features.search_trips.address_search.model

import kotlinx.serialization.Serializable

@Serializable
data class AddressSearchVmState(
    val query: String = "",
)
