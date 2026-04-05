package com.al4apps.travelers2.di

import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.core.database.SharedDatabase
import com.al4apps.travelers2.core.settings.AppSettingsFactory
import com.al4apps.travelers2.features.home.data.DefaultHomePlaceholderRepository
import com.al4apps.travelers2.features.home.data.SharedDatabaseHomeBootstrapRepository
import com.al4apps.travelers2.features.onboarding.data.DefaultOnboardingPlaceholderRepository
import com.al4apps.travelers2.features.root.data.SettingsAppLaunchRepository
import com.al4apps.travelers2.features.root.domain.usecase.CompleteFirstLaunchUseCase
import com.al4apps.travelers2.features.root.domain.usecase.GetInitialRouteUseCase

class RootDiContainer(
    appSettingsFactory: AppSettingsFactory,
    databaseDriverFactory: DatabaseDriverFactory,
) {

    private val appSettings = appSettingsFactory.create()
    private val sharedDatabase = SharedDatabase(databaseDriverFactory)
    private val appLaunchRepository = SettingsAppLaunchRepository(appSettings)
    private val homeBootstrapRepository = SharedDatabaseHomeBootstrapRepository(sharedDatabase)
    private val homePlaceholderRepository = DefaultHomePlaceholderRepository()
    private val onboardingPlaceholderRepository = DefaultOnboardingPlaceholderRepository()

    val getInitialRouteUseCase = GetInitialRouteUseCase(appLaunchRepository)
    val completeFirstLaunchUseCase = CompleteFirstLaunchUseCase(appLaunchRepository)

    fun createHomeDiContainer(): HomeDiContainer =
        HomeDiContainer(
            homeBootstrapRepository = homeBootstrapRepository,
            homePlaceholderRepository = homePlaceholderRepository,
        )

    fun createOnboardingDiContainer(): OnboardingDiContainer =
        OnboardingDiContainer(
            onboardingPlaceholderRepository = onboardingPlaceholderRepository,
        )
}
