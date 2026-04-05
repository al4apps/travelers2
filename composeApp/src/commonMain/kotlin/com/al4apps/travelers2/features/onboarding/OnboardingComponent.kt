package com.al4apps.travelers2.features.onboarding

import com.al4apps.travelers2.features.onboarding.model.OnboardingUiState
import com.arkivanov.decompose.value.Value

interface OnboardingComponent {
    val uiState: Value<OnboardingUiState>

    fun onContinueClick()
}
