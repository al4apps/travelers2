package com.al4apps.travelers2.features.search_trips.model

import com.al4apps.travelers2.core.ui.UiText

data class SearchTripsUiState(
    val title: UiText,
    val fromAddress: String,
    val toAddress: String,
    val tripDate: String,
    val fromPlaceholder: UiText,
    val toPlaceholder: UiText,
    val datePlaceholder: UiText,
    val searchButtonText: UiText,
)
