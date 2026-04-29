package com.al4apps.travelers2.features.create_trip.confirm.model

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.RouteTimelinePoint
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState

data class CreateTripConfirmUiState(
    val topBarState: HomeTopBarUiState,
    val title: UiText,
    val driverName: String,
    val driverMeta: String,
    val dateTimeLabel: UiText,
    val dateText: String,
    val departureTimeText: String,
    val arrivalTimeText: String?,
    val routeLabel: UiText,
    val departure: RouteTimelinePoint,
    val arrival: RouteTimelinePoint,
    val vehicleLabel: UiText,
    val vehicleModel: String,
    val vehiclePlate: String,
    val conditionsLabel: UiText,
    val conditions: List<ConditionRow>,
    val hint: UiText,
    val publishButtonUiState: ButtonUiState,
    val errorMessage: String?,
)

data class ConditionRow(
    val label: UiText,
    val value: UiText,
)
