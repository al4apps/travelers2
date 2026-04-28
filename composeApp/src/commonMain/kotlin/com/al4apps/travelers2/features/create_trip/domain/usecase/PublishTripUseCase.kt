package com.al4apps.travelers2.features.create_trip.domain.usecase

import com.al4apps.travelers2.features.create_trip.domain.repository.PublishTripRepository
import com.al4apps.travelers2.features.create_trip.domain.repository.RecentTripsRepository
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.model.RecentTrip

class PublishTripUseCase(
    private val publishRepository: PublishTripRepository,
    private val recentTripsRepository: RecentTripsRepository,
) {
    suspend operator fun invoke(draft: CreateTripDraft): RecentTrip {
        check(draft.isRouteStepValid && draft.isTimeStepValid && draft.isVehicleStepValid) {
            "Draft is not complete"
        }
        val recentTrip = publishRepository.publish(draft)
        recentTripsRepository.add(recentTrip)
        return recentTrip
    }
}
