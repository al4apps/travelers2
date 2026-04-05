package com.al4apps.travelers2.features.home.domain.usecase

import com.al4apps.travelers2.features.home.domain.model.HomePlaceholder
import com.al4apps.travelers2.features.home.domain.repository.HomePlaceholderRepository

class GetHomePlaceholderUseCase(
    private val homePlaceholderRepository: HomePlaceholderRepository,
) {

    /**
     * Loads placeholder content for the Home feature skeleton.
     */
    operator fun invoke(): HomePlaceholder = homePlaceholderRepository.getPlaceholder()
}
