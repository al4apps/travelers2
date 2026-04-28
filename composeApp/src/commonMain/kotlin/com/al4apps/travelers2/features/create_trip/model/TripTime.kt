package com.al4apps.travelers2.features.create_trip.model

import kotlinx.serialization.Serializable

@Serializable
data class TripTime(
    val hour: Int,
    val minute: Int,
) {
    init {
        require(hour in 0..23) { "hour must be in 0..23, got $hour" }
        require(minute in 0..59) { "minute must be in 0..59, got $minute" }
    }
}

object TripTimeFormatter {
    fun toDisplayString(time: TripTime): String {
        val h = time.hour.toString().padStart(2, '0')
        val m = time.minute.toString().padStart(2, '0')
        return "$h:$m"
    }
}
