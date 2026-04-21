package com.al4apps.travelers2.features.trip_details

import com.al4apps.travelers2.features.trip_details.model.TripDetailsUiState
import com.arkivanov.decompose.value.Value

interface TripDetailsComponent {
    val uiState: Value<TripDetailsUiState>

    fun onBackClick()

    fun onBookClick()
}
