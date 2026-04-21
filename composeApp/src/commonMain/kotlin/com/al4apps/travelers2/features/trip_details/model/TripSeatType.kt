package com.al4apps.travelers2.features.trip_details.model

import kotlinx.serialization.Serializable

@Serializable
enum class TripSeatType {
    DRIVER,
    OCCUPIED,
    AVAILABLE,
}
