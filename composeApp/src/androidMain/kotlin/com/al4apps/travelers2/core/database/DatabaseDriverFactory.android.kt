package com.al4apps.travelers2.core.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.al4apps.travelers2.core.platform.PlatformContext

private const val DATABASE_NAME = "travelers.db"

actual class DatabaseDriverFactory actual constructor(
    private val platformContext: PlatformContext,
) {

    actual suspend fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = TravelersDatabase.Schema,
            context = platformContext.applicationContext,
            name = DATABASE_NAME,
        )
}
