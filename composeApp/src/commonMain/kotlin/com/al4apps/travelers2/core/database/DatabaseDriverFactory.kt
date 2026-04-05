package com.al4apps.travelers2.core.database

import app.cash.sqldelight.db.SqlDriver
import com.al4apps.travelers2.core.platform.PlatformContext

expect class DatabaseDriverFactory(platformContext: PlatformContext) {
    suspend fun createDriver(): SqlDriver
}
