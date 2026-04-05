package com.al4apps.travelers2.features.home

import com.al4apps.travelers2.core.ui.UiText
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewHomeComponent : HomeComponent {
    override val uiState: Value<HomeUiState> =
        MutableValue(
            HomeUiState(
                isLoading = false,
                title = UiText.Plain("Home"),
                message = UiText.Plain("Preview state for the base Home feature."),
            ),
        )

    override fun onRetryClick() = Unit
}
