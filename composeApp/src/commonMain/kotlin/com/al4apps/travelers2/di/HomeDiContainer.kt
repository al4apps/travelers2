package com.al4apps.travelers2.di

import com.al4apps.travelers2.features.home.domain.repository.HomeBootstrapRepository
import com.al4apps.travelers2.features.home.domain.usecase.InitializeHomeUseCase
import com.al4apps.travelers2.features.home.map.HomeUiAssembler

class HomeDiContainer(
    homeBootstrapRepository: HomeBootstrapRepository,
) {

    val initializeHomeUseCase = InitializeHomeUseCase(
        homeBootstrapRepository = homeBootstrapRepository,
    )

    val homeUiAssembler = HomeUiAssembler()
}
