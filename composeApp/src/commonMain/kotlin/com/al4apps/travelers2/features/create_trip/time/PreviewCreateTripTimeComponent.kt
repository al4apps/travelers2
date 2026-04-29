package com.al4apps.travelers2.features.create_trip.time

import com.al4apps.travelers2.features.create_trip.time.map.CreateTripTimeUiAssembler
import com.al4apps.travelers2.features.create_trip.time.model.CreateTripTimeUiState
import com.al4apps.travelers2.features.create_trip.time.model.CreateTripTimeVmState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewCreateTripTimeComponent : CreateTripTimeComponent {
    override val uiState: Value<CreateTripTimeUiState> = MutableValue(
        CreateTripTimeUiAssembler().assemble(CreateTripTimeVmState()),
    )
    override val dialogSlot: Value<ChildSlot<TimeDialogConfig, TimeDialogChild>> =
        MutableValue(ChildSlot())

    override fun onBackClick() = Unit
    override fun onDepartureClick() = Unit
    override fun onMeetingChange(value: String) = Unit
    override fun onIncrementHours() = Unit
    override fun onDecrementHours() = Unit
    override fun onIncrementMinutes() = Unit
    override fun onDecrementMinutes() = Unit
    override fun onContinueClick() = Unit
}
