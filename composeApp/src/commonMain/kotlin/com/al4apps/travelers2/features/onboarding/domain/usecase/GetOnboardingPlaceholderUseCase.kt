package com.al4apps.travelers2.features.onboarding.domain.usecase

import com.al4apps.travelers2.features.onboarding.domain.model.OnboardingPlaceholder
import com.al4apps.travelers2.features.onboarding.domain.repository.OnboardingPlaceholderRepository

class GetOnboardingPlaceholderUseCase(
    private val onboardingPlaceholderRepository: OnboardingPlaceholderRepository,
) {

    /**
     * Loads placeholder content for the onboarding feature.
     */
    operator fun invoke(): OnboardingPlaceholder = onboardingPlaceholderRepository.getPlaceholder()
}
