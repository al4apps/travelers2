package com.al4apps.travelers2.features.root.domain.usecase

import com.al4apps.travelers2.features.root.domain.repository.AppLaunchRepository

class CompleteFirstLaunchUseCase(
    private val appLaunchRepository: AppLaunchRepository,
) {

    /**
     * Persists onboarding completion so the next app launch can open Home directly.
     */
    operator fun invoke() {
        appLaunchRepository.completeFirstLaunch()
    }
}
