package com.al4apps.travelers2.core.platform

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri

@SuppressLint("UseKtx")
class ContactAppLauncherAndroid(private val context: Context) : ContactAppLauncher {


    override fun openDialer(phoneNumber: String): Boolean = openIntent(
        intent = Intent(
            Intent.ACTION_DIAL,
            Uri.parse("tel:${phoneNumber.sanitizePhoneNumber()}"),
        ),
    )

    override fun openWhatsApp(phoneNumber: String): Boolean = openIntent(
        intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://wa.me/${phoneNumber.sanitizePhoneNumber()}"),
        ),
    )

    override fun openTelegram(username: String): Boolean = openIntent(
        intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://t.me/${username.trim().removePrefix("@")}"),
        ),
    )

    private fun openIntent(intent: Intent): Boolean =
        runCatching {
            context.startActivity(
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK),
            )
            true
        }.getOrDefault(false)
}

private fun String.sanitizePhoneNumber(): String =
    filter { it.isDigit() }

actual class ContactAppLauncherFactory(private val context: Context) {
    actual fun create(): ContactAppLauncher {
        return ContactAppLauncherAndroid(context.applicationContext)
    }
}