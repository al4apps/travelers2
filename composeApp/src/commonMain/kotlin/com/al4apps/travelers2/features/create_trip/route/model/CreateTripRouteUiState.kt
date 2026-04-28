package com.al4apps.travelers2.features.create_trip.route.model

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.RecentTripCardState
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState

data class CreateTripRouteUiState(
    val topBarState: HomeTopBarUiState,
    val title: UiText,
    val fromValue: String,
    val toValue: String,
    val dateValue: String,
    val fromPlaceholder: UiText,
    val toPlaceholder: UiText,
    val datePlaceholder: UiText,
    val continueButtonUiState: ButtonUiState,
    val recentSectionTitle: UiText,
    val recentEmptyText: UiText,
    val recentRepeatLabel: UiText,
    val recentTrips: List<RecentTripCardState>,
)
