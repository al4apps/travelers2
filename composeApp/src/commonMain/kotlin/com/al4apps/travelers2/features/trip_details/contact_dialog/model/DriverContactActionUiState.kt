package com.al4apps.travelers2.features.trip_details.contact_dialog.model

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.trip_details.contact_dialog.DriverContactAction

data class DriverContactActionUiState(
    val action: DriverContactAction,
    val title: UiText,
    val style: DriverContactActionStyle,
)
