package com.al4apps.travelers2.di

import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.core.database.SharedDatabase
import com.al4apps.travelers2.core.platform.ContactAppLauncherFactory
import com.al4apps.travelers2.core.settings.AppSettings
import com.al4apps.travelers2.features.create_trip.data.InMemoryPublishTripRepository
import com.al4apps.travelers2.features.create_trip.data.InMemoryRecentTripsRepository
import com.al4apps.travelers2.features.create_trip.data.InMemoryUserVehiclesRepository
import com.al4apps.travelers2.features.home.data.SharedDatabaseHomeBootstrapRepository
import com.al4apps.travelers2.features.onboarding.data.DefaultOnboardingPlaceholderRepository
import com.al4apps.travelers2.features.root.data.SettingsAppLaunchRepository
import com.al4apps.travelers2.features.root.domain.usecase.CompleteFirstLaunchUseCase
import com.al4apps.travelers2.features.root.domain.usecase.GetInitialRouteUseCase
import com.al4apps.travelers2.features.trip_details.data.PlatformDriverContactRepository
import com.al4apps.travelers2.features.trip_details.domain.usecase.HandleDriverContactActionUseCase

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

    // CreateTrip dependencies — single instances at root level so recent trips list
    // is shared across CreateTrip instantiations within an app session.
    private val recentTripsRepository = InMemoryRecentTripsRepository()
    private val userVehiclesRepository = InMemoryUserVehiclesRepository()
    private val publishTripRepository = InMemoryPublishTripRepository()

    val getInitialRouteUseCase = GetInitialRouteUseCase(appLaunchRepository)
    val completeFirstLaunchUseCase = CompleteFirstLaunchUseCase(appLaunchRepository)
    private val handleDriverContactActionUseCase =
        HandleDriverContactActionUseCase(driverContactRepository)

    fun createHomeDiContainer(): HomeDiContainer =
        HomeDiContainer(
            homeBootstrapRepository = homeBootstrapRepository,
            driverContactActionUseCase = handleDriverContactActionUseCase,
            createTripDiContainerFactory = ::createCreateTripDiContainer,
        )

    fun createOnboardingDiContainer(): OnboardingDiContainer =
        OnboardingDiContainer(
            onboardingPlaceholderRepository = onboardingPlaceholderRepository,
        )

    private fun createCreateTripDiContainer(): CreateTripDiContainer =
        CreateTripDiContainer(
            recentTripsRepository = recentTripsRepository,
            userVehiclesRepository = userVehiclesRepository,
            publishTripRepository = publishTripRepository,
        )
}
