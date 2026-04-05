package com.al4apps.travelers2.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.al4apps.travelers2.App
import com.al4apps.travelers2.core.platform.PlatformContext
import com.al4apps.travelers2.core.settings.AppSettingsFactory
import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.di.RootDiContainer
import com.al4apps.travelers2.features.root.DefaultRootComponent
import com.arkivanov.decompose.retainedComponent

class MainActivity : ComponentActivity() {

    private val rootComponent by lazy(LazyThreadSafetyMode.NONE) {
        retainedComponent { componentContext ->
            DefaultRootComponent(
                componentContext = componentContext,
                diContainer = RootDiContainer(
                    appSettingsFactory = AppSettingsFactory(
                        platformContext = PlatformContext(applicationContext = applicationContext),
                    ),
                    databaseDriverFactory = DatabaseDriverFactory(
                        platformContext = PlatformContext(applicationContext = applicationContext),
                    ),
                ),
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(rootComponent = rootComponent)
        }
    }
}
