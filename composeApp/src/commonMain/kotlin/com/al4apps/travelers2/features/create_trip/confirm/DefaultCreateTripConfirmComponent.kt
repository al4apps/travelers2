package com.al4apps.travelers2.features.create_trip.confirm

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.features.create_trip.confirm.map.CreateTripConfirmUiAssembler
import com.al4apps.travelers2.features.create_trip.confirm.model.CreateTripConfirmUiState
import com.al4apps.travelers2.features.create_trip.confirm.model.CreateTripConfirmVmState
import com.al4apps.travelers2.features.create_trip.domain.usecase.GetUserVehiclesUseCase
import com.al4apps.travelers2.features.create_trip.domain.usecase.PublishTripUseCase
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.model.Vehicle
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class DefaultCreateTripConfirmComponent(
    componentContext: ComponentContext,
    private val draft: CreateTripDraft,
    private val publishTripUseCase: PublishTripUseCase,
    private val getUserVehiclesUseCase: GetUserVehiclesUseCase,
    private val onBack: () -> Unit,
    private val onPublished: () -> Unit,
    private val uiAssembler: CreateTripConfirmUiAssembler = CreateTripConfirmUiAssembler(),
) : CreateTripConfirmComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val vmStateStore = StateStore(
        initialState = CreateTripConfirmVmState(draft = draft),
    )

    private var loadedVehicle: Vehicle? = null

    private val mutableUiState: MutableValue<CreateTripConfirmUiState> = MutableValue(
        uiAssembler.assemble(vmState = vmStateStore.value, vehicle = null),
    )

    override val uiState: Value<CreateTripConfirmUiState> = mutableUiState

    init {
        vmStateStore.stateFlow
            .onEach { reassemble() }
            .launchIn(featureScope)
        loadVehicle()
    }

    override fun onBackClick() {
        if (vmStateStore.value.isPublishing) return
        onBack.invoke()
    }

    override fun onPublishClick() {
        if (vmStateStore.value.isPublishing) return
        vmStateStore.updateState { it.copy(isPublishing = true, errorMessage = null) }
        featureScope.launch {
            runCatching { publishTripUseCase(draft) }
                .onSuccess { onPublished.invoke() }
                .onFailure { throwable ->
                    vmStateStore.updateState {
                        it.copy(
                            isPublishing = false,
                            errorMessage = throwable.message ?: "Не удалось опубликовать поездку",
                        )
                    }
                }
        }
    }

    private fun loadVehicle() {
        val targetId = draft.selectedVehicleId ?: return
        featureScope.launch {
            runCatching { getUserVehiclesUseCase() }
                .onSuccess { vehicles ->
                    loadedVehicle = vehicles.firstOrNull { it.id == targetId }
                    reassemble()
                }
        }
    }

    private fun reassemble() {
        mutableUiState.value = uiAssembler.assemble(
            vmState = vmStateStore.value,
            vehicle = loadedVehicle,
        )
    }
}
