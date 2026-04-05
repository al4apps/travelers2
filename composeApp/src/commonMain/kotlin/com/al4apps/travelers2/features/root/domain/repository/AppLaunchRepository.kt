package com.al4apps.travelers2.features.root.domain.repository

interface AppLaunchRepository {

    /**
     * Returns `true`, when the app should show onboarding before opening the main feature.
     */
    fun isFirstLaunch(): Boolean

    /**
     * Marks the first-launch flow as completed for the current app installation.
     */
    fun completeFirstLaunch()
}
