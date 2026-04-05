package com.al4apps.travelers2.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.al4apps.travelers2.core.platform.PlatformContext

private const val DATABASE_NAME = "travelers.db"

actual class DatabaseDriverFactory actual constructor(
    @Suppress("UNUSED_PARAMETER")
    private val platformContext: PlatformContext,
) {

    actual suspend fun createDriver(): SqlDriver =
        NativeSqliteDriver(
            schema = TravelersDatabase.Schema,
            name = DATABASE_NAME,
        )
}
