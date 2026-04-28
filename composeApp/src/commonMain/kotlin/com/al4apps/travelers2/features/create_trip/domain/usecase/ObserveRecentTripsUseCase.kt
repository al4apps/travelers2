package com.al4apps.travelers2.features.create_trip.domain.usecase

import com.al4apps.travelers2.features.create_trip.domain.repository.RecentTripsRepository
import com.al4apps.travelers2.features.create_trip.model.RecentTrip
import kotlinx.coroutines.flow.Flow

class ObserveRecentTripsUseCase(
    private val repository: RecentTripsRepository,
) {
    operator fun invoke(): Flow<List<RecentTrip>> = repository.observe()
}
