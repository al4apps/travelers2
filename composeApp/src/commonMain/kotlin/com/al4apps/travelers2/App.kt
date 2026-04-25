package com.al4apps.travelers2

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.core.platform.ContactAppLauncherFactory
import com.al4apps.travelers2.core.settings.AppSettings
import com.al4apps.travelers2.di.RootDiContainer
import com.al4apps.travelers2.features.root.DefaultRootComponent
import com.al4apps.travelers2.features.root.ui.RootScreen
import com.al4apps.travelers2.theme.AppTheme
import com.arkivanov.decompose.ComponentContext

@Composable
fun App(
    componentContext: ComponentContext,
    appSettings: AppSettings,
    databaseDriverFactory: DatabaseDriverFactory,
    contactAppLauncherFactory: ContactAppLauncherFactory,
    modifier: Modifier = Modifier,
) {
    val rootComponent =
        DefaultRootComponent(
            componentContext = componentContext,
            diContainer = RootDiContainer(
                appSettings = appSettings,
                databaseDriverFactory = databaseDriverFactory,
                contactAppLauncherFactory = contactAppLauncherFactory,
            ),
        )
    AppTheme {
        RootScreen(
            component = rootComponent,
            modifier = modifier,
        )
    }
}
