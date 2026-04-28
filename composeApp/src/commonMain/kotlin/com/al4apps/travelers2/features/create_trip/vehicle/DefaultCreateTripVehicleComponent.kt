package com.al4apps.travelers2.features.create_trip.vehicle

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.create_trip.domain.usecase.GetUserVehiclesUseCase
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.vehicle.map.CreateTripVehicleUiAssembler
import com.al4apps.travelers2.features.create_trip.vehicle.model.CreateTripVehicleUiState
import com.al4apps.travelers2.features.create_trip.vehicle.model.CreateTripVehicleVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.launch

class DefaultCreateTripVehicleComponent(
    componentContext: ComponentContext,
    initialDraft: CreateTripDraft,
    private val getUserVehiclesUseCase: GetUserVehiclesUseCase,
    private val onCommitVehicle: (
        vehicleId: String,
        freeSeatsCount: Int,
        pricePerSeat: Int,
        baggageAllowed: Boolean,
        parcelDeliveryAllowed: Boolean,
    ) -> Unit,
    private val onBack: () -> Unit,
    private val uiAssembler: CreateTripVehicleUiAssembler = CreateTripVehicleUiAssembler(),
) : CreateTripVehicleComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = CreateTripVehicleVmState.serializer(),
        ) ?: CreateTripVehicleVmState(
            isLoading = true,
            selectedVehicleId = initialDraft.selectedVehicleId,
            freeSeatsCount = initialDraft.freeSeatsCount,
            priceInput = initialDraft.pricePerSeat?.toString().orEmpty(),
            baggageAllowed = initialDraft.baggageAllowed,
            parcelDeliveryAllowed = initialDraft.parcelDeliveryAllowed,
        ),
    )

    override val uiState: Value<CreateTripVehicleUiState> = vmStateStore.toUiState(
        scope = featureScope,
        assemble = uiAssembler::assemble,
    )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = CreateTripVehicleVmState.serializer(),
            supplier = vmStateStore::value,
        )
        loadVehicles()
    }

    override fun onBackClick() {
        onBack.invoke()
    }

    override fun onVehicleSelected(vehicleId: String) {
        vmStateStore.updateState { it.copy(selectedVehicleId = vehicleId) }
    }

    override fun onIncrementSeats() {
        vmStateStore.updateState {
            if (it.freeSeatsCount >= CreateTripDraft.MAX_FREE_SEATS) it
            else it.copy(freeSeatsCount = it.freeSeatsCount + 1)
        }
    }

    override fun onDecrementSeats() {
        vmStateStore.updateState {
            if (it.freeSeatsCount <= CreateTripDraft.MIN_FREE_SEATS) it
            else it.copy(freeSeatsCount = it.freeSeatsCount - 1)
        }
    }

    override fun onPriceChange(value: String) {
        val sanitized = value.filter { it.isDigit() }.take(MAX_PRICE_LENGTH)
        vmStateStore.updateState { it.copy(priceInput = sanitized) }
    }

    override fun onBaggageToggle(value: Boolean) {
        vmStateStore.updateState { it.copy(baggageAllowed = value) }
    }

    override fun onParcelToggle(value: Boolean) {
        vmStateStore.updateState { it.copy(parcelDeliveryAllowed = value) }
    }

    override fun onContinueClick() {
        val state = vmStateStore.value
        val vehicleId = state.selectedVehicleId ?: return
        val price = state.priceInput.toIntOrNull() ?: return
        if (price <= 0) return
        if (state.freeSeatsCount !in CreateTripDraft.MIN_FREE_SEATS..CreateTripDraft.MAX_FREE_SEATS) return
        onCommitVehicle(
            vehicleId,
            state.freeSeatsCount,
            price,
            state.baggageAllowed,
            state.parcelDeliveryAllowed,
        )
    }

    override fun onRetryClick() {
        loadVehicles()
    }

    private fun loadVehicles() {
        vmStateStore.updateState { it.copy(isLoading = true, errorMessage = null) }
        featureScope.launch {
            runCatching { getUserVehiclesUseCase() }
                .onSuccess { vehicles ->
                    vmStateStore.updateState { current ->
                        val selected = current.selectedVehicleId
                            ?.takeIf { id -> vehicles.any { it.id == id } }
                            ?: vehicles.firstOrNull()?.id
                        current.copy(
                            isLoading = false,
                            errorMessage = null,
                            vehicles = vehicles,
                            selectedVehicleId = selected,
                        )
                    }
                }
                .onFailure { throwable ->
                    vmStateStore.updateState {
                        it.copy(
                            isLoading = false,
                            errorMessage = throwable.message ?: "Failed to load vehicles",
                        )
                    }
                }
        }
    }

    private companion object {
        const val STATE_KEY = "create_trip_vehicle_vm_state"
        const val MAX_PRICE_LENGTH = 6
    }
}
