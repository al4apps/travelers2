package com.al4apps.travelers2.features.home

import com.arkivanov.decompose.value.Value

interface HomeComponent {
    val uiState: Value<HomeUiState>

    fun onRetryClick()
}
