package com.al4apps.travelers2.features.create_trip.time.model

import com.al4apps.travelers2.features.create_trip.model.TripDuration
import com.al4apps.travelers2.features.create_trip.model.TripTime
import kotlinx.serialization.Serializable

@Serializable
data class CreateTripTimeVmState(
    val departureTime: TripTime? = null,
    val duration: TripDuration = TripDuration.ZERO,
    val meetingPoint: String = "",
)
