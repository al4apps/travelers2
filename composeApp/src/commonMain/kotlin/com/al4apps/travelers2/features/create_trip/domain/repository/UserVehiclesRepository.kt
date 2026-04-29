package com.al4apps.travelers2.features.create_trip.domain.repository

import com.al4apps.travelers2.features.create_trip.model.Vehicle

interface UserVehiclesRepository {
    suspend fun getVehicles(): List<Vehicle>
}
