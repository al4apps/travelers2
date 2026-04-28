package com.al4apps.travelers2.features.create_trip.vehicle

import com.al4apps.travelers2.features.create_trip.vehicle.model.CreateTripVehicleUiState
import com.arkivanov.decompose.value.Value

interface CreateTripVehicleComponent {
    val uiState: Value<CreateTripVehicleUiState>

    fun onBackClick()
    fun onVehicleSelected(vehicleId: String)
    fun onIncrementSeats()
    fun onDecrementSeats()
    fun onPriceChange(value: String)
    fun onBaggageToggle(value: Boolean)
    fun onParcelToggle(value: Boolean)
    fun onContinueClick()
    fun onRetryClick()
}
