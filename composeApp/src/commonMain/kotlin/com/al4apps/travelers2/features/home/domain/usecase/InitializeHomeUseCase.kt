package com.al4apps.travelers2.features.home.domain.usecase

import com.al4apps.travelers2.features.home.domain.repository.HomeBootstrapRepository

class InitializeHomeUseCase(
    private val homeBootstrapRepository: HomeBootstrapRepository,
) {

    /**
     * Runs the Home bootstrap sequence and completes when all technical prerequisites are initialized.
     */
    suspend operator fun invoke() {
        homeBootstrapRepository.initialize()
    }
}
