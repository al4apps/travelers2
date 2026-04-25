package com.al4apps.travelers2.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.al4apps.travelers2.App
import com.arkivanov.decompose.defaultComponentContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val app = application as MainApp
        setContent {
            App(
                componentContext = defaultComponentContext(),
                appSettings = app.appSettingsFactory.create(),
                databaseDriverFactory = app.databaseDriverFactory,
                contactAppLauncherFactory = app.contactAppLauncherFactory,
            )
        }
    }
}
