package com.al4apps.travelers2.features.onboarding

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.onboarding.model.OnboardingUiState
import com.arkivanov.decompose.value.MutableValue
import com.arkivanov.decompose.value.Value

class PreviewOnboardingComponent : OnboardingComponent {
    override val uiState: Value<OnboardingUiState> =
        MutableValue(
            OnboardingUiState(
                title = UiText.Plain("Onboarding"),
                message = UiText.Plain("Preview state for the onboarding placeholder."),
                buttonLabel = UiText.Plain("Continue"),
            ),
        )

    override fun onContinueClick() = Unit
}
