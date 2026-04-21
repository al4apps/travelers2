package com.al4apps.travelers2.features.trip_details.model

import kotlinx.serialization.Serializable

@Serializable
data class TripDriverVmModel(
    val name: String,
    val completedTripsCount: Int,
)
