package com.al4apps.travelers2

import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.window.ComposeUIViewController
import com.al4apps.travelers2.core.database.DatabaseDriverFactory
import com.al4apps.travelers2.core.platform.ContactAppLauncherFactory
import com.al4apps.travelers2.core.settings.AppSettings
import com.al4apps.travelers2.core.settings.AppSettingsFactory
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.backhandler.BackDispatcher
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.destroy
import com.arkivanov.essenty.lifecycle.resume
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    val lifecycle = LifecycleRegistry()
    val backDispatcher = BackDispatcher()
    val componentContext = DefaultComponentContext(lifecycle, backHandler = backDispatcher)
    val appSettings: AppSettings = AppSettingsFactory().create()
    val databaseDriverFactory = DatabaseDriverFactory()
    val contactAppLauncherFactory = ContactAppLauncherFactory()
    return ComposeUIViewController {
        DisposableEffect(Unit) {
            lifecycle.resume()
            onDispose(lifecycle::destroy)
        }

        App(
            componentContext = componentContext,
            appSettings = appSettings,
            databaseDriverFactory = databaseDriverFactory,
            contactAppLauncherFactory = contactAppLauncherFactory,
        )
    }
}
