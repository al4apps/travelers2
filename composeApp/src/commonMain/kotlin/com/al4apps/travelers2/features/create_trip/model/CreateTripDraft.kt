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
    val freeSeatsCount: Int? = null,
    val pricePerSeat: Int? = null,
    val baggageAllowed: Boolean = true,
    val parcelDeliveryAllowed: Boolean = true,
) {
    val isRouteStepValid: Boolean
        get() = fromAddress != null && toAddress != null && tripDate != null

    /** meetingPoint is optional. */
    val isTimeStepValid: Boolean
        get() = departureTime != null

    val isVehicleStepValid: Boolean
        get() = selectedVehicleId != null &&
            (freeSeatsCount ?: 0) >= MIN_FREE_SEATS &&
            pricePerSeat != null && pricePerSeat > 0

    fun arrivalTime(): TripTime? {
        val departure = departureTime ?: return null
        val duration = duration ?: return null
        if (duration.isZero) return null
        return duration.applyTo(departure)
    }

    companion object {
        const val MIN_FREE_SEATS = 1

        /** Hard cap; effective max is bound by selected vehicle's totalSeats - 1 (driver). */
        const val MAX_FREE_SEATS_HARD_CAP = 8
    }
}
