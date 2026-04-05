package com.al4apps.travelers2.features.onboarding.map

import com.al4apps.travelers2.core.ui.UiText
import com.al4apps.travelers2.features.onboarding.model.OnboardingUiState
import com.al4apps.travelers2.features.onboarding.model.OnboardingVmState

class OnboardingUiAssembler {

    fun assemble(vmState: OnboardingVmState): OnboardingUiState =
        OnboardingUiState(
            title = UiText.Plain(vmState.title.ifBlank { "Onboarding" }),
            message = UiText.Plain(vmState.message),
            buttonLabel = UiText.Plain(vmState.buttonLabel.ifBlank { "Continue" }),
        )
}
