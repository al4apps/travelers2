package com.al4apps.travelers2.features.create_trip.confirm

import com.al4apps.travelers2.features.create_trip.confirm.model.CreateTripConfirmUiState
import com.arkivanov.decompose.value.Value

interface CreateTripConfirmComponent {
    val uiState: Value<CreateTripConfirmUiState>

    fun onBackClick()
    fun onPublishClick()
}
