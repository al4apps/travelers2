package com.al4apps.travelers2.features.trip_details.model

import kotlinx.serialization.Serializable

@Serializable
data class TripDetailsVmState(
    val tripId: String,
    val tripDateText: String,
    val driver: TripDriverVmModel,
    val departurePoint: TripPointVmModel,
    val arrivalPoint: TripPointVmModel,
    val car: TripCarVmModel,
    val baggageAllowed: Boolean,
    val parcelDeliveryAllowed: Boolean,
    val freeSeatsCount: Int,
    val seats: List<TripSeatVmModel>,
    val priceText: String,
)
