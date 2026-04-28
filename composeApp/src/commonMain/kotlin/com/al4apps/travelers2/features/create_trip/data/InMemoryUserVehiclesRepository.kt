package com.al4apps.travelers2.features.create_trip.data

import com.al4apps.travelers2.features.create_trip.domain.repository.UserVehiclesRepository
import com.al4apps.travelers2.features.create_trip.model.Vehicle

class InMemoryUserVehiclesRepository : UserVehiclesRepository {
    override suspend fun getVehicles(): List<Vehicle> = vehicles

    private companion object {
        val vehicles = listOf(
            Vehicle(
                id = "vehicle_camry",
                model = "Toyota Camry",
                plateNumber = "0001 TJ 01",
                totalSeats = 4,
            ),
            Vehicle(
                id = "vehicle_sonata",
                model = "Hyundai Sonata",
                plateNumber = "0123 TJ 01",
                totalSeats = 4,
            ),
            Vehicle(
                id = "vehicle_starex",
                model = "Hyundai Starex",
                plateNumber = "0456 TJ 01",
                totalSeats = 7,
            ),
        )
    }
}
