package com.al4apps.travelers2.features.onboarding.domain.repository

import com.al4apps.travelers2.features.onboarding.domain.model.OnboardingPlaceholder

interface OnboardingPlaceholderRepository {

    /**
     * Returns placeholder content for the onboarding feature scaffold.
     */
    fun getPlaceholder(): OnboardingPlaceholder
}
