package com.al4apps.travelers2.features.create_trip.time

import com.al4apps.travelers2.features.create_trip.time.model.CreateTripTimeUiState
import com.arkivanov.decompose.router.slot.ChildSlot
import com.arkivanov.decompose.value.Value

interface CreateTripTimeComponent {
    val uiState: Value<CreateTripTimeUiState>
    val dialogSlot: Value<ChildSlot<TimeDialogConfig, TimeDialogChild>>

    fun onBackClick()
    fun onDepartureClick()
    fun onMeetingChange(value: String)
    fun onIncrementHours()
    fun onDecrementHours()
    fun onIncrementMinutes()
    fun onDecrementMinutes()
    fun onContinueClick()
}
