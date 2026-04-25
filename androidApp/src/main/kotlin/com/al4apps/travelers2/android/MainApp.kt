package com.al4apps.travelers2.android

import android.app.Application
import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.core.platform.ContactAppLauncherFactory
import com.al4apps.travelers2.core.settings.AppSettingsFactory

class MainApp : Application() {

    lateinit var appSettingsFactory: AppSettingsFactory
    lateinit var databaseDriverFactory: DatabaseDriverFactory
    lateinit var contactAppLauncherFactory: ContactAppLauncherFactory

    override fun onCreate() {
        super.onCreate()
        appSettingsFactory = AppSettingsFactory(this)
        databaseDriverFactory = DatabaseDriverFactory(this)
        contactAppLauncherFactory = ContactAppLauncherFactory(this)
    }
}