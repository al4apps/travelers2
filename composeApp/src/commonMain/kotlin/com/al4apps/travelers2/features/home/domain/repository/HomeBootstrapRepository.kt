package com.al4apps.travelers2.features.home.domain.repository

interface HomeBootstrapRepository {

    /**
     * Performs technical initialization required before Home can render stable content.
     */
    suspend fun initialize()
}
