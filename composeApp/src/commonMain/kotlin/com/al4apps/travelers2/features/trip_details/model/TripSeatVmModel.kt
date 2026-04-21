package com.al4apps.travelers2.features.trip_details.model

import kotlinx.serialization.Serializable

@Serializable
data class TripSeatVmModel(
    val id: String,
    val type: TripSeatType,
)
