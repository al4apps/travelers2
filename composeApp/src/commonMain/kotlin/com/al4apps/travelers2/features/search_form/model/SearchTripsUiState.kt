package com.al4apps.travelers2.features.search_form.model

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.shared_model.ButtonUiState

data class SearchTripsUiState(
    val title: UiText,
    val fromAddress: String,
    val toAddress: String,
    val tripDate: String,
    val fromPlaceholder: UiText,
    val toPlaceholder: UiText,
    val datePlaceholder: UiText,
    val searchButtonUiState: ButtonUiState,
)
