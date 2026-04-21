package com.al4apps.travelers2.features.trip_details.model

import kotlinx.serialization.Serializable

@Serializable
data class TripCarVmModel(
    val model: String,
    val plateNumber: String,
)
