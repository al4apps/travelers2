package com.al4apps.travelers2

import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.window.ComposeUIViewController
import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.core.platform.PlatformContext
import com.al4apps.travelers2.core.settings.AppSettingsFactory
import com.al4apps.travelers2.di.RootDiContainer
import com.al4apps.travelers2.features.root.DefaultRootComponent
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val lifecycle = LifecycleRegistry()
    val platformContext = PlatformContext()
    val rootComponent = DefaultRootComponent(
        componentContext = DefaultComponentContext(lifecycle = lifecycle),
        diContainer = RootDiContainer(
            appSettingsFactory = AppSettingsFactory(platformContext = platformContext),
            databaseDriverFactory = DatabaseDriverFactory(platformContext = platformContext),
        ),
    )

    return ComposeUIViewController {
        DisposableEffect(Unit) {
            lifecycle.resume()
            onDispose(lifecycle::destroy)
        }

        App(rootComponent = rootComponent)
    }
}
