package com.al4apps.travelers2.core.log

import android.util.Log

actual fun createKmpLogger(tag: String): KmpLogger = KmpLoggerAndroid(tag)

class KmpLoggerAndroid(private val tag: String) : KmpLogger {
    override fun debug(message: String, t: Throwable?) {
        Log.d(tag, message, t)

    }
}