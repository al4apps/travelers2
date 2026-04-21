package com.al4apps.travelers2.features.trip_details.model

import kotlinx.serialization.Serializable

@Serializable
data class TripPointVmModel(
    val time: String,
    val city: String,
    val district: String,
)
