package com.al4apps.travelers2.features.create_trip.confirm

import com.al4apps.travelers2.features.create_trip.confirm.map.CreateTripConfirmUiAssembler
import com.al4apps.travelers2.features.create_trip.confirm.model.CreateTripConfirmUiState
import com.al4apps.travelers2.features.create_trip.confirm.model.CreateTripConfirmVmState
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewCreateTripConfirmComponent : CreateTripConfirmComponent {
    override val uiState: Value<CreateTripConfirmUiState> = MutableValue(
        CreateTripConfirmUiAssembler().assemble(
            vmState = CreateTripConfirmVmState(draft = CreateTripDraft()),
            vehicle = null,
        ),
    )

    override fun onBackClick() = Unit
    override fun onPublishClick() = Unit
}
