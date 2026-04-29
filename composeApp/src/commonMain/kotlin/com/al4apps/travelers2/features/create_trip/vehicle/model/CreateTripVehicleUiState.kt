package com.al4apps.travelers2.features.create_trip.vehicle.model

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.design_system.VehicleCarouselItemState
import com.al4apps.travelers2.shared_model.ButtonUiState
import com.al4apps.travelers2.shared_model.HomeTopBarUiState

data class CreateTripVehicleUiState(
    val topBarState: HomeTopBarUiState,
    val isLoading: Boolean,
    val errorMessage: String?,
    val sectionTitle: UiText,
    val vehicles: List<VehicleCarouselItemState>,
    val seatsTitle: UiText,
    val seatsSubtitle: UiText,
    val seatsValue: String,
    val canDecrementSeats: Boolean,
    val canIncrementSeats: Boolean,
    val priceTitle: UiText,
    val priceInput: String,
    val priceCurrency: UiText,
    val priceHint: UiText,
    val pricePlaceholder: UiText,
    val extrasTitle: UiText,
    val baggageLabel: UiText,
    val parcelLabel: UiText,
    val baggageAllowed: Boolean,
    val parcelAllowed: Boolean,
    val continueButtonUiState: ButtonUiState,
)
