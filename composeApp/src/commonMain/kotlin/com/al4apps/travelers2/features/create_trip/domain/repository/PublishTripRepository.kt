package com.al4apps.travelers2.features.create_trip.domain.repository

import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.model.RecentTrip

interface PublishTripRepository {
    suspend fun publish(draft: CreateTripDraft): RecentTrip
}
