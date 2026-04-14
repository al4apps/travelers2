package com.al4apps.travelers2.shared_model

import com.al4apps.travelers2.core.ui.UiText
import kotlinx.serialization.Serializable

@Serializable
data class ButtonUiState(
    val text: UiText,
    val enabled: Boolean
)
