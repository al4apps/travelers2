package com.al4apps.travelers2.core.database

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SharedDatabase(
    private val driverFactory: DatabaseDriverFactory,
) {

    private val mutex = Mutex()
    private var instance: TravelersDatabase? = null

    suspend fun init(): TravelersDatabase =
        mutex.withLock {
            instance ?: TravelersDatabase(
                driver = driverFactory.createDriver(),
            ).also { database ->
                instance = database
            }
        }

    suspend operator fun <T> invoke(
        block: suspend (TravelersDatabase) -> T,
    ): T = block(init())
}
