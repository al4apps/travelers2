package com.al4apps.travelers2.core.platform

import platform.Foundation.NSURL
import platform.UIKit.UIApplication

class ContactAppLauncherIOS : ContactAppLauncher {

    override fun openDialer(phoneNumber: String): Boolean =
        openUrl("tel:${phoneNumber.sanitizePhoneNumber()}")

    override fun openWhatsApp(phoneNumber: String): Boolean =
        openUrl("https://wa.me/${phoneNumber.sanitizePhoneNumber()}")

    override fun openTelegram(username: String): Boolean =
        openUrl("https://t.me/${username.trim().removePrefix("@")}")

    private fun openUrl(url: String): Boolean {
        val nsUrl = NSURL.URLWithString(url) ?: return false
        return UIApplication.sharedApplication.openURL(nsUrl)
    }
}

private fun String.sanitizePhoneNumber(): String =
    filter { it.isDigit() }

actual class ContactAppLauncherFactory {
    actual fun create(): ContactAppLauncher = ContactAppLauncherIOS()
}