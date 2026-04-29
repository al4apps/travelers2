package com.al4apps.travelers2.features.create_trip.domain.usecase

import com.al4apps.travelers2.core.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

class ObserveAuthStateUseCase(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Flow<Boolean> = authRepository.observeAuthState()
}
