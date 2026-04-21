package com.al4apps.travelers2.features.trip_details.model

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.shared_model.HomeTopBarUiState
import org.jetbrains.compose.resources.DrawableResource

data class TripDetailsUiState(
    val topBarState: HomeTopBarUiState,
    val driverAvatarRes: DrawableResource,
    val driverName: UiText,
    val driverTripsText: UiText,
    val departureTime: UiText,
    val departureCity: UiText,
    val departureDistrict: UiText,
    val arrivalTime: UiText,
    val arrivalCity: UiText,
    val arrivalDistrict: UiText,
    val carImageRes: DrawableResource,
    val carModel: UiText,
    val carPlateNumber: UiText,
    val options: List<TripDetailsOptionUiModel>,
    val seatsTitle: UiText,
    val seats: List<TripDetailsSeatUiModel>,
    val priceTitle: UiText,
    val priceValue: UiText,
    val bookingButtonTitle: UiText,
)
