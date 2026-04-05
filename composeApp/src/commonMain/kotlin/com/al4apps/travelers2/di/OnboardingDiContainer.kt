package com.al4apps.travelers2.di

import com.al4apps.travelers2.features.onboarding.domain.repository.OnboardingPlaceholderRepository
import com.al4apps.travelers2.features.onboarding.domain.usecase.GetOnboardingPlaceholderUseCase
import com.al4apps.travelers2.features.onboarding.map.OnboardingUiAssembler

class OnboardingDiContainer(
    onboardingPlaceholderRepository: OnboardingPlaceholderRepository,
) {

    val getOnboardingPlaceholderUseCase = GetOnboardingPlaceholderUseCase(
        onboardingPlaceholderRepository = onboardingPlaceholderRepository,
    )

    val onboardingUiAssembler = OnboardingUiAssembler()
}
