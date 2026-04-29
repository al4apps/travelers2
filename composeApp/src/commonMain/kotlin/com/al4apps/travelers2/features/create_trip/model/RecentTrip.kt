package com.al4apps.travelers2.features.create_trip.model

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate

data class RecentTrip(
    val id: String,
    val fromAddress: TripAddress,
    val toAddress: TripAddress,
    val tripDate: TripDate,
    val status: RecentTripStatus,
)

enum class RecentTripStatus {
    PUBLISHED,
    ON_MODERATION,
    COMPLETED,
}
