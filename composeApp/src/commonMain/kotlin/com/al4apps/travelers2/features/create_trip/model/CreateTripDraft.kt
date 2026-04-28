package com.al4apps.travelers2.features.create_trip.model

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import kotlinx.serialization.Serializable

@Serializable
data class CreateTripDraft(
    val fromAddress: TripAddress? = null,
    val toAddress: TripAddress? = null,
    val tripDate: TripDate? = null,
    val departureTime: TripTime? = null,
    val duration: TripDuration? = null,
    val meetingPoint: String = "",
    val selectedVehicleId: String? = null,
    val freeSeatsCount: Int = DEFAULT_FREE_SEATS,
    val pricePerSeat: Int? = null,
    val baggageAllowed: Boolean = false,
    val parcelDeliveryAllowed: Boolean = false,
) {
    val isRouteStepValid: Boolean
        get() = fromAddress != null && toAddress != null && tripDate != null

    val isTimeStepValid: Boolean
        get() = departureTime != null && meetingPoint.isNotBlank()

    val isVehicleStepValid: Boolean
        get() = selectedVehicleId != null && freeSeatsCount in MIN_FREE_SEATS..MAX_FREE_SEATS &&
            pricePerSeat != null && pricePerSeat > 0

    fun arrivalTime(): TripTime? {
        val departure = departureTime ?: return null
        val duration = duration ?: return null
        if (duration.isZero) return null
        return duration.applyTo(departure)
    }

    companion object {
        const val MIN_FREE_SEATS = 1
        const val MAX_FREE_SEATS = 8
        const val DEFAULT_FREE_SEATS = 1
    }
}
