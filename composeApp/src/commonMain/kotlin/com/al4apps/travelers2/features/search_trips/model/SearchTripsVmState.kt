package com.al4apps.travelers2.features.search_trips.model

import kotlinx.serialization.Serializable

@Serializable
data class SearchTripsVmState(
    val fromAddress: String = "",
    val toAddress: String = "",
    val tripDate: String = "",
)
