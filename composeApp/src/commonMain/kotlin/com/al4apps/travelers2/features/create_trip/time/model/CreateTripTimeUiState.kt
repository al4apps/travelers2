package com.al4apps.travelers2.features.create_trip.time.model

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState

data class CreateTripTimeUiState(
    val topBarState: HomeTopBarUiState,
    val title: UiText,
    val subtitle: UiText,
    val departureLabel: UiText,
    val departureValue: String,
    val departurePlaceholder: UiText,
    val durationLabel: UiText,
    val durationOptional: UiText,
    val durationHoursLabel: UiText,
    val durationMinutesLabel: UiText,
    val durationHoursValue: String,
    val durationMinutesValue: String,
    val canDecrementHours: Boolean,
    val canIncrementHours: Boolean,
    val canDecrementMinutes: Boolean,
    val canIncrementMinutes: Boolean,
    val arrivalText: UiText,
    val meetingLabel: UiText,
    val meetingValue: String,
    val meetingPlaceholder: UiText,
    val continueButtonUiState: ButtonUiState,
)
