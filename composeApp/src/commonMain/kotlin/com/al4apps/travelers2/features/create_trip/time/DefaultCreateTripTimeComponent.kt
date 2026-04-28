package com.al4apps.travelers2.features.create_trip.time

import com.al4apps.travelers2.core.CoroutineFeature
import com.al4apps.travelers2.core.CoroutineFeatureImpl
import com.al4apps.travelers2.core.StateStore
import com.al4apps.travelers2.core.toUiState
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.model.TripDuration
import com.al4apps.travelers2.features.create_trip.model.TripTime
import com.al4apps.travelers2.features.create_trip.time.map.CreateTripTimeUiAssembler
import com.al4apps.travelers2.features.create_trip.time.model.CreateTripTimeUiState
import com.al4apps.travelers2.features.create_trip.time.model.CreateTripTimeVmState
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.activate
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.dismiss
import com.arkivanov.decompose.value.Value

class DefaultCreateTripTimeComponent(
    componentContext: ComponentContext,
    initialDraft: CreateTripDraft,
    private val onCommitTime: (
        departureTime: TripTime,
        duration: TripDuration,
        meetingPoint: String,
    ) -> Unit,
    private val onBack: () -> Unit,
    private val uiAssembler: CreateTripTimeUiAssembler = CreateTripTimeUiAssembler(),
) : CreateTripTimeComponent,
    ComponentContext by componentContext,
    CoroutineFeature by CoroutineFeatureImpl(componentContext) {

    private val dialogNavigation = SlotNavigation<TimeDialogConfig>()

    private val vmStateStore = StateStore(
        initialState = stateKeeper.consume(
            key = STATE_KEY,
            strategy = CreateTripTimeVmState.serializer(),
        ) ?: CreateTripTimeVmState(
            departureTime = initialDraft.departureTime,
            duration = initialDraft.duration ?: TripDuration.ZERO,
            meetingPoint = initialDraft.meetingPoint,
        ),
    )

    override val uiState: Value<CreateTripTimeUiState> = vmStateStore.toUiState(
        scope = featureScope,
        assemble = uiAssembler::assemble,
    )

    override val dialogSlot: Value<ChildSlot<TimeDialogConfig, TimeDialogChild>> = childSlot(
        source = dialogNavigation,
        serializer = TimeDialogConfig.serializer(),
        key = DIALOG_KEY,
        handleBackButton = true,
        childFactory = ::createDialogChild,
    )

    init {
        stateKeeper.register(
            key = STATE_KEY,
            strategy = CreateTripTimeVmState.serializer(),
            supplier = vmStateStore::value,
        )
    }

    override fun onBackClick() {
        onBack.invoke()
    }

    override fun onDepartureClick() {
        val current = vmStateStore.value.departureTime ?: TripTime(hour = 9, minute = 0)
        dialogNavigation.activate(
            TimeDialogConfig.TimePicker(
                initialHour = current.hour,
                initialMinute = current.minute,
            ),
        )
    }

    override fun onMeetingChange(value: String) {
        vmStateStore.updateState { it.copy(meetingPoint = value) }
    }

    override fun onIncrementHours() {
        vmStateStore.updateState {
            val current = it.duration
            if (current.hours >= TripDuration.MAX_HOURS) it
            else it.copy(duration = current.copy(hours = current.hours + 1))
        }
    }

    override fun onDecrementHours() {
        vmStateStore.updateState {
            val current = it.duration
            if (current.hours <= 0) it
            else it.copy(duration = current.copy(hours = current.hours - 1))
        }
    }

    override fun onIncrementMinutes() {
        vmStateStore.updateState {
            val current = it.duration
            val next = current.minutes + MINUTES_STEP
            if (next > MINUTES_MAX) it
            else it.copy(duration = current.copy(minutes = next))
        }
    }

    override fun onDecrementMinutes() {
        vmStateStore.updateState {
            val current = it.duration
            val next = current.minutes - MINUTES_STEP
            if (next < 0) it
            else it.copy(duration = current.copy(minutes = next))
        }
    }

    override fun onContinueClick() {
        val state = vmStateStore.value
        val time = state.departureTime ?: return
        if (state.meetingPoint.isBlank()) return
        onCommitTime(time, state.duration, state.meetingPoint)
    }

    private fun createDialogChild(
        config: TimeDialogConfig,
        @Suppress("UNUSED_PARAMETER") componentContext: ComponentContext,
    ): TimeDialogChild =
        when (config) {
            is TimeDialogConfig.TimePicker -> TimeDialogChild.TimePicker(
                initial = TripTime(hour = config.initialHour, minute = config.initialMinute),
                onConfirm = ::applyDeparture,
                onDismiss = dialogNavigation::dismiss,
            )
        }

    private fun applyDeparture(time: TripTime) {
        vmStateStore.updateState { it.copy(departureTime = time) }
        dialogNavigation.dismiss()
    }

    private companion object {
        const val STATE_KEY = "create_trip_time_vm_state"
        const val DIALOG_KEY = "create_trip_time_dialog"
        const val MINUTES_STEP = 5
        const val MINUTES_MAX = 55
    }
}
