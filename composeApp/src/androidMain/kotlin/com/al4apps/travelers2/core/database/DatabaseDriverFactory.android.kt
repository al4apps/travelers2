package com.al4apps.travelers2.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

private const val DATABASE_NAME = "travelers.db"

actual class DatabaseDriverFactory(
    private val context: Context,
) {

    actual suspend fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = TravelersDatabase.Schema,
            context = context.applicationContext,
            name = DATABASE_NAME,
        )
}
