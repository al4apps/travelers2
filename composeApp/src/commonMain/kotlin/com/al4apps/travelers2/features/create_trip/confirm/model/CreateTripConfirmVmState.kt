package com.al4apps.travelers2.features.create_trip.confirm.model

import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import kotlinx.serialization.Serializable

@Serializable
data class CreateTripConfirmVmState(
    val draft: CreateTripDraft,
    val driverName: String = DEFAULT_DRIVER_NAME,
    val driverRating: String = DEFAULT_DRIVER_RATING,
    val driverTripsText: String = DEFAULT_DRIVER_TRIPS,
    val isPublishing: Boolean = false,
    val errorMessage: String? = null,
) {
    private companion object {
        const val DEFAULT_DRIVER_NAME = "Азамат Ибраимов"
        const val DEFAULT_DRIVER_RATING = "4.9"
        const val DEFAULT_DRIVER_TRIPS = "124 поездки"
    }
}
