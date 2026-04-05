package com.al4apps.travelers2.features.onboarding.data

import com.al4apps.travelers2.features.onboarding.domain.model.OnboardingPlaceholder
import com.al4apps.travelers2.features.onboarding.domain.repository.OnboardingPlaceholderRepository

class DefaultOnboardingPlaceholderRepository : OnboardingPlaceholderRepository {

    override fun getPlaceholder(): OnboardingPlaceholder =
        OnboardingPlaceholder(
            title = "Onboarding",
            message = "First-launch onboarding placeholder component.",
            buttonLabel = "Continue",
        )
}
