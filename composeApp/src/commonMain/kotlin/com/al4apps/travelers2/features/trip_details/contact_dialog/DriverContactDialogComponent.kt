package com.al4apps.travelers2.features.trip_details.contact_dialog

import androidx.compose.runtime.Stable
import com.al4apps.travelers2.core.dialog.BaseContentDialogComponent
import com.al4apps.travelers2.features.trip_details.contact_dialog.model.DriverContactDialogUiState
import com.arkivanov.decompose.value.Value

@Stable
interface DriverContactDialogComponent : BaseContentDialogComponent {
    val contactActionsState: Value<DriverContactDialogUiState>

    fun onActionClick(action: DriverContactAction)
}
