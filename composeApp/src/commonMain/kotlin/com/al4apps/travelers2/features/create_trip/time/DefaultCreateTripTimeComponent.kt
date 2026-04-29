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

/**
 * Time step. Mirrors the relevant slice of the parent draft into its own [CreateTripTimeVmState].
 *
 * Mutations are applied locally; the slice is committed to the parent draft on every UI mutation
 * via [onSliceChanged] so that "back-and-forward" navigation does not lose progress.
 *
 * The "Continue" callback ([onCommitTime]) is invoked only when the slice is fully valid.
 */
class DefaultCreateTripTimeComponent(
    componentContext: ComponentContext,
    initialDraft: CreateTripDraft,
    private val onSliceChanged: (
        departureTime: TripTime?,
        duration: TripDuration?,
        meetingPoint: String,
    ) -> Unit,
    private val onCommitTime: () -> Unit,
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
        propagateSlice()
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
        mutate { it.copy(meetingPoint = value.take(MEETING_MAX_LENGTH)) }
    }

    override fun onIncrementHours() {
        mutate {
            val current = it.duration
            if (current.hours >= TripDuration.MAX_HOURS) it
            else it.copy(duration = current.copy(hours = current.hours + 1))
        }
    }

    override fun onDecrementHours() {
        mutate {
            val current = it.duration
            if (current.hours <= 0) it
            else it.copy(duration = current.copy(hours = current.hours - 1))
        }
    }

    override fun onIncrementMinutes() {
        mutate {
            val current = it.duration
            val next = current.minutes + MINUTES_STEP
            if (next > MINUTES_MAX) it
            else it.copy(duration = current.copy(minutes = next))
        }
    }

    override fun onDecrementMinutes() {
        mutate {
            val current = it.duration
            val next = current.minutes - MINUTES_STEP
            if (next < 0) it
            else it.copy(duration = current.copy(minutes = next))
        }
    }

    override fun onContinueClick() {
        val state = vmStateStore.value
        if (state.departureTime == null) return
        propagateSlice()
        onCommitTime()
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
        mutate { it.copy(departureTime = time) }
        dialogNavigation.dismiss()
    }

    private fun mutate(block: (CreateTripTimeVmState) -> CreateTripTimeVmState) {
        vmStateStore.updateState(block)
        propagateSlice()
    }

    private fun propagateSlice() {
        val state = vmStateStore.value
        onSliceChanged(
            state.departureTime,
            state.duration.takeUnless { it.isZero },
            state.meetingPoint,
        )
    }

    private companion object {
        const val STATE_KEY = "create_trip_time_vm_state"
        const val DIALOG_KEY = "create_trip_time_dialog"
        const val MINUTES_STEP = 5
        const val MINUTES_MAX = 55
        const val MEETING_MAX_LENGTH = 200
    }
}
