package com.al4apps.travelers2.features.create_trip.data

import com.al4apps.travelers2.features.create_trip.domain.repository.PublishTripRepository
import com.al4apps.travelers2.features.create_trip.model.CreateTripDraft
import com.al4apps.travelers2.features.create_trip.model.RecentTrip
import com.al4apps.travelers2.features.create_trip.model.RecentTripStatus
import kotlin.random.Random

class InMemoryPublishTripRepository : PublishTripRepository {

    override suspend fun publish(draft: CreateTripDraft): RecentTrip {
        val from = checkNotNull(draft.fromAddress) { "fromAddress required" }
        val to = checkNotNull(draft.toAddress) { "toAddress required" }
        val date = checkNotNull(draft.tripDate) { "tripDate required" }
        val id = "trip_${Random.nextLong().toString(radix = 36)}"
        return RecentTrip(
            id = id,
            fromAddress = from,
            toAddress = to,
            tripDate = date,
            status = RecentTripStatus.ON_MODERATION,
        )
    }
}
