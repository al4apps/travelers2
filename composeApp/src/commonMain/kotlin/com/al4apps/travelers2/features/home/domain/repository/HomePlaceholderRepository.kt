package com.al4apps.travelers2.features.home.domain.repository

import com.al4apps.travelers2.features.home.domain.model.HomePlaceholder

interface HomePlaceholderRepository {

    /**
     * Returns placeholder content for the base Home feature scaffold.
     */
    fun getPlaceholder(): HomePlaceholder
}
