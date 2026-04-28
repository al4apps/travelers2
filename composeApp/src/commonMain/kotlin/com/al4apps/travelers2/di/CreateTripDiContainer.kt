package com.al4apps.travelers2.di

import com.al4apps.travelers2.features.create_trip.domain.repository.PublishTripRepository
import com.al4apps.travelers2.features.create_trip.domain.repository.RecentTripsRepository
import com.al4apps.travelers2.features.create_trip.domain.repository.UserVehiclesRepository
import com.al4apps.travelers2.features.create_trip.domain.usecase.GetUserVehiclesUseCase
import com.al4apps.travelers2.features.create_trip.domain.usecase.ObserveRecentTripsUseCase
import com.al4apps.travelers2.features.create_trip.domain.usecase.PublishTripUseCase

class CreateTripDiContainer(
    recentTripsRepository: RecentTripsRepository,
    userVehiclesRepository: UserVehiclesRepository,
    publishTripRepository: PublishTripRepository,
) {
    val observeRecentTripsUseCase = ObserveRecentTripsUseCase(
        repository = recentTripsRepository,
    )

    val getUserVehiclesUseCase = GetUserVehiclesUseCase(
        repository = userVehiclesRepository,
    )

    val publishTripUseCase = PublishTripUseCase(
        publishRepository = publishTripRepository,
        recentTripsRepository = recentTripsRepository,
    )
}
