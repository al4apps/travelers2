package com.al4apps.travelers2.core.platform

interface ContactAppLauncher {
    fun openDialer(phoneNumber: String): Boolean

    fun openWhatsApp(phoneNumber: String): Boolean

    fun openTelegram(username: String): Boolean
}

expect class ContactAppLauncherFactory {
    fun create(): ContactAppLauncher
}
