package com.al4apps.travelers2.features.trip_details.model

import com.al4apps.travelers2.core.ui.UiText
import org.jetbrains.compose.resources.DrawableResource

data class TripDetailsOptionUiModel(
    val iconRes: DrawableResource,
    val title: UiText,
    val isAvailable: Boolean,
)
