package com.al4apps.travelers2.di

import com.al4apps.travelers2.core.platform.ContactAppLauncher
import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.core.database.SharedDatabase
import com.al4apps.travelers2.core.platform.ContactAppLauncherFactory
import com.al4apps.travelers2.core.settings.AppSettings
import com.al4apps.travelers2.core.settings.AppSettingsFactory
import com.al4apps.travelers2.features.home.data.SharedDatabaseHomeBootstrapRepository
import com.al4apps.travelers2.features.onboarding.data.DefaultOnboardingPlaceholderRepository
import com.al4apps.travelers2.features.root.data.SettingsAppLaunchRepository
import com.al4apps.travelers2.features.trip_details.data.PlatformDriverContactRepository
import com.al4apps.travelers2.features.trip_details.domain.usecase.HandleDriverContactActionUseCase
import com.al4apps.travelers2.features.root.domain.usecase.CompleteFirstLaunchUseCase
import com.al4apps.travelers2.features.root.domain.usecase.GetInitialRouteUseCase

class RootDiContainer(
    appSettings: AppSettings,
    databaseDriverFactory: DatabaseDriverFactory,
    contactAppLauncherFactory: ContactAppLauncherFactory,
) {

    private val sharedDatabase = SharedDatabase(databaseDriverFactory)
    private val appLaunchRepository = SettingsAppLaunchRepository(appSettings)
    private val homeBootstrapRepository = SharedDatabaseHomeBootstrapRepository(sharedDatabase)
    private val onboardingPlaceholderRepository = DefaultOnboardingPlaceholderRepository()
    private val driverContactRepository = PlatformDriverContactRepository(
        contactAppLauncher = contactAppLauncherFactory.create(),
    )

    val getInitialRouteUseCase = GetInitialRouteUseCase(appLaunchRepository)
    val completeFirstLaunchUseCase = CompleteFirstLaunchUseCase(appLaunchRepository)
    private val handleDriverContactActionUseCase = HandleDriverContactActionUseCase(driverContactRepository)

    fun createHomeDiContainer(): HomeDiContainer =
        HomeDiContainer(
            homeBootstrapRepository = homeBootstrapRepository,
            driverContactActionUseCase = handleDriverContactActionUseCase,
        )

    fun createOnboardingDiContainer(): OnboardingDiContainer =
        OnboardingDiContainer(
            onboardingPlaceholderRepository = onboardingPlaceholderRepository,
        )
}
