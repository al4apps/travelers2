package com.al4apps.travelers2.features.home.model

import com.al4apps.travelers2.core.ui.UiText

data class HomeUiState(
    val isLoading: Boolean,
    val isDockVisible: Boolean,
    val errorMessage: UiText? = null,
)
