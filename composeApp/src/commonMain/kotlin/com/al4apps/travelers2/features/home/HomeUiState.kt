package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.core.ui.UiText

data class HomeUiState(
    val isLoading: Boolean,
    val title: UiText,
    val message: UiText,
    val errorMessage: UiText? = null,
)
