package com.al4apps.travelers2.features.root.domain.usecase

import com.al4apps.travelers2.features.root.domain.RootStartDestination
import com.al4apps.travelers2.features.root.domain.repository.AppLaunchRepository

class GetInitialRouteUseCase(
    private val appLaunchRepository: AppLaunchRepository,
) {

    /**
     * Resolves the initial root destination from persisted app-launch state.
     */
    operator fun invoke(): RootStartDestination =
        if (appLaunchRepository.isFirstLaunch()) {
            RootStartDestination.ONBOARDING
        } else {
            RootStartDestination.HOME
        }
}
