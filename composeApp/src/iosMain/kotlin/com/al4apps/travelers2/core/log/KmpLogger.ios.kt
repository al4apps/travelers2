package com.al4apps.travelers2.core.log

import platform.Foundation.NSLog

actual fun createKmpLogger(tag: String): KmpLogger = KMPLoggerIOS(tag)

class KMPLoggerIOS(val tag: String) : KmpLogger {
    override fun debug(message: String, t: Throwable?) {
        NSLog("$tag :: $message \n${t?.message}")
    }
}