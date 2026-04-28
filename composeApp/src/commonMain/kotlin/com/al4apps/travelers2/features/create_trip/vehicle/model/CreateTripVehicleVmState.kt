package com.al4apps.travelers2.features.create_trip.vehicle.model

import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.model.Vehicle
import kotlinx.serialization.Serializable

@Serializable
data class CreateTripVehicleVmState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val vehicles: List<Vehicle> = emptyList(),
    val selectedVehicleId: String? = null,
    val freeSeatsCount: Int = CreateTripDraft.DEFAULT_FREE_SEATS,
    val priceInput: String = "",
    val baggageAllowed: Boolean = false,
    val parcelDeliveryAllowed: Boolean = false,
)
