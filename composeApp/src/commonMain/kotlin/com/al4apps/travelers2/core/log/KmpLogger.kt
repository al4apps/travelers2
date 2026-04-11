package com.al4apps.travelers2.core.log

interface KmpLogger {
    fun debug(message: String, t: Throwable? = null)
}

expect fun createKmpLogger(tag: String): KmpLogger

inline fun <reified T> kmpLogger(): KmpLogger {
    return createKmpLogger(T::class.simpleName ?: "Unknown")
}