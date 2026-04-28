package com.al4apps.travelers2.features.create_trip.vehicle

import com.al4apps.travelers2.features.create_trip.vehicle.map.CreateTripVehicleUiAssembler
import com.al4apps.travelers2.features.create_trip.vehicle.model.CreateTripVehicleUiState
import com.al4apps.travelers2.features.create_trip.vehicle.model.CreateTripVehicleVmState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewCreateTripVehicleComponent : CreateTripVehicleComponent {
    override val uiState: Value<CreateTripVehicleUiState> = MutableValue(
        CreateTripVehicleUiAssembler().assemble(
            CreateTripVehicleVmState(isLoading = false),
        ),
    )

    override fun onBackClick() = Unit
    override fun onVehicleSelected(vehicleId: String) = Unit
    override fun onIncrementSeats() = Unit
    override fun onDecrementSeats() = Unit
    override fun onPriceChange(value: String) = Unit
    override fun onBaggageToggle(value: Boolean) = Unit
    override fun onParcelToggle(value: Boolean) = Unit
    override fun onContinueClick() = Unit
    override fun onRetryClick() = Unit
}
