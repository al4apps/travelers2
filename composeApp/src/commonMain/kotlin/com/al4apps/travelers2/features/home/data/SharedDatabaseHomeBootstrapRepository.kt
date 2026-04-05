package com.al4apps.travelers2.features.home.data

import com.al4apps.travelers2.core.database.SharedDatabase
import com.al4apps.travelers2.features.home.domain.repository.HomeBootstrapRepository

class SharedDatabaseHomeBootstrapRepository(
    private val sharedDatabase: SharedDatabase,
) : HomeBootstrapRepository {

    override suspend fun initialize() {
        sharedDatabase.init()
    }
}
