package com.al4apps.travelers2.features.create_trip.domain.repository

import com.al4apps.travelers2.features.create_trip.model.RecentTrip
import kotlinx.coroutines.flow.Flow

interface RecentTripsRepository {
    fun observe(): Flow<List<RecentTrip>>

    suspend fun add(recentTrip: RecentTrip)
}
