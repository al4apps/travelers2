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
    /**
     * Mirrors current UI slice into the parent draft on every mutation so back/forward
     * navigation does not lose progress.
     */
    private val onSliceChanged: (
        vehicleId: String?,
        freeSeatsCount: Int?,
        pricePerSeat: Int?,
        baggageAllowed: Boolean,
        parcelDeliveryAllowed: Boolean,
    ) -> Unit,
    private val onCommitVehicle: () -> Unit,
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
        propagateSlice()
        onBack.invoke()
    }

    override fun onVehicleSelected(vehicleId: String) {
        mutate { state ->
            val newSelected = state.vehicles.firstOrNull { it.id == vehicleId }
            val newSeats = newSelected?.let { it.totalSeats - 1 }
            state.copy(
                selectedVehicleId = vehicleId,
                freeSeatsCount = newSeats,
            )
        }
    }

    override fun onIncrementSeats() {
        mutate { state ->
            val selected = state.vehicles.firstOrNull { it.id == state.selectedVehicleId }
            val max = selected?.let { it.totalSeats - 1 } ?: return@mutate state
            val current = state.freeSeatsCount ?: max
            if (current >= max) state
            else state.copy(freeSeatsCount = current + 1)
        }
    }

    override fun onDecrementSeats() {
        mutate { state ->
            val current = state.freeSeatsCount ?: return@mutate state
            if (current <= CreateTripDraft.MIN_FREE_SEATS) state
            else state.copy(freeSeatsCount = current - 1)
        }
    }

    override fun onPriceChange(value: String) {
        val sanitized = value.filter { it.isDigit() }.take(MAX_PRICE_LENGTH)
        mutate { it.copy(priceInput = sanitized) }
    }

    override fun onBaggageToggle(value: Boolean) {
        mutate { it.copy(baggageAllowed = value) }
    }

    override fun onParcelToggle(value: Boolean) {
        mutate { it.copy(parcelDeliveryAllowed = value) }
    }

    override fun onContinueClick() {
        val state = vmStateStore.value
        val selected = state.vehicles.firstOrNull { it.id == state.selectedVehicleId } ?: return
        val seats = state.freeSeatsCount ?: return
        val price = state.priceInput.toIntOrNull() ?: return
        if (price <= 0) return
        val maxSeats = selected.totalSeats - 1
        if (seats !in CreateTripDraft.MIN_FREE_SEATS..maxSeats) return
        propagateSlice()
        onCommitVehicle()
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
                        val previousId = current.selectedVehicleId
                        val matchedById = vehicles.firstOrNull { it.id == previousId }
                        val selected = matchedById ?: vehicles.firstOrNull()
                        val seats = current.freeSeatsCount
                            ?: selected?.let { it.totalSeats - 1 }
                        current.copy(
                            isLoading = false,
                            errorMessage = null,
                            vehicles = vehicles,
                            selectedVehicleId = selected?.id,
                            freeSeatsCount = seats,
                        )
                    }
                    propagateSlice()
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

    private fun mutate(block: (CreateTripVehicleVmState) -> CreateTripVehicleVmState) {
        vmStateStore.updateState(block)
        propagateSlice()
    }

    private fun propagateSlice() {
        val state = vmStateStore.value
        onSliceChanged(
            state.selectedVehicleId,
            state.freeSeatsCount,
            state.priceInput.toIntOrNull(),
            state.baggageAllowed,
            state.parcelDeliveryAllowed,
        )
    }

    private companion object {
        const val STATE_KEY = "create_trip_vehicle_vm_state"
        const val MAX_PRICE_LENGTH = 6
    }
}
