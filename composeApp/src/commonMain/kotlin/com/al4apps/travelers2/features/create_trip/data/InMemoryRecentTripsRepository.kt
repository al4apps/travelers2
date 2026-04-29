package com.al4apps.travelers2.features.create_trip.data

import com.al4apps.travelers2.core.model.TripAddress
import com.al4apps.travelers2.core.model.TripDate
import com.al4apps.travelers2.features.create_trip.domain.repository.RecentTripsRepository
import com.al4apps.travelers2.features.create_trip.model.RecentTrip
import com.al4apps.travelers2.features.create_trip.model.RecentTripStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class InMemoryRecentTripsRepository : RecentTripsRepository {

    private val state = MutableStateFlow(seed())

    override fun observe(): Flow<List<RecentTrip>> = state.asStateFlow()

    override suspend fun add(recentTrip: RecentTrip) {
        state.update { current -> listOf(recentTrip) + current }
    }

    private fun seed(): List<RecentTrip> = listOf(
        RecentTrip(
            id = "recent_1",
            fromAddress = TripAddress(
                country = "Таджикистан",
                region = "Душанбе",
                town = "Душанбе",
                locality = "Фирдавси",
            ),
            toAddress = TripAddress(
                country = "Таджикистан",
                region = "Согдийская область",
                town = "Худжанд",
                locality = "Панчшанбе",
            ),
            tripDate = TripDate(year = 2025, month = 10, day = 12),
            status = RecentTripStatus.COMPLETED,
        ),
        RecentTrip(
            id = "recent_2",
            fromAddress = TripAddress(
                country = "Таджикистан",
                region = "Душанбе",
                town = "Душанбе",
                locality = null,
            ),
            toAddress = TripAddress(
                country = "Таджикистан",
                region = "Хатлонская область",
                town = "Бохтар",
                locality = null,
            ),
            tripDate = TripDate(year = 2025, month = 10, day = 8),
            status = RecentTripStatus.COMPLETED,
        ),
        RecentTrip(
            id = "recent_3",
            fromAddress = TripAddress(
                country = "Таджикистан",
                region = "Согдийская область",
                town = "Худжанд",
                locality = null,
            ),
            toAddress = TripAddress(
                country = "Таджикистан",
                region = "Районы республиканского подчинения",
                town = "Пенджикент",
                locality = null,
            ),
            tripDate = TripDate(year = 2025, month = 10, day = 5),
            status = RecentTripStatus.COMPLETED,
        ),
    )
}
