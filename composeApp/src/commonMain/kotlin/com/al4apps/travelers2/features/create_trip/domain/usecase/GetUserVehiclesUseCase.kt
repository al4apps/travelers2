package com.al4apps.travelers2.features.create_trip.domain.usecase

import com.al4apps.travelers2.features.create_trip.domain.repository.UserVehiclesRepository
import com.al4apps.travelers2.features.create_trip.model.Vehicle

class GetUserVehiclesUseCase(
    private val repository: UserVehiclesRepository,
) {
    suspend operator fun invoke(): List<Vehicle> = repository.getVehicles()
}
