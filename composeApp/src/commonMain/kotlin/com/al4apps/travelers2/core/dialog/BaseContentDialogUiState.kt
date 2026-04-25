package com.al4apps.travelers2.core.dialog

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.shared_model.ButtonUiState

data class BaseContentDialogUiState(
    val title: UiText,
    val text: UiText,
    val primaryButton: ButtonUiState? = null,
    val secondaryButton: ButtonUiState? = null,
)
