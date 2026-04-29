package com.al4apps.travelers2.features.create_trip.vehicle.model

import com.al4apps.travelers2.features.create_trip.model.Vehicle
import kotlinx.serialization.Serializable

@Serializable
data class CreateTripVehicleVmState(
    val isLoading: Boolean = true,
    val errorMessage: String? = null,
    val vehicles: List<Vehicle> = emptyList(),
    val selectedVehicleId: String? = null,
    val freeSeatsCount: Int? = null,
    val priceInput: String = "",
    val baggageAllowed: Boolean = true,
    val parcelDeliveryAllowed: Boolean = true,
)
