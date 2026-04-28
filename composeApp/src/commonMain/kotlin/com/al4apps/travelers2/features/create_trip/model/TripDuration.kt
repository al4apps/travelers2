package com.al4apps.travelers2.features.create_trip.model

import kotlinx.serialization.Serializable

@Serializable
data class TripDuration(
    val hours: Int,
    val minutes: Int,
) {
    init {
        require(hours in 0..MAX_HOURS) { "hours must be in 0..$MAX_HOURS" }
        require(minutes in 0..59) { "minutes must be in 0..59" }
    }

    val isZero: Boolean get() = hours == 0 && minutes == 0

    fun applyTo(time: TripTime): TripTime {
        val totalMinutes = time.hour * 60 + time.minute + hours * 60 + minutes
        val normalized = ((totalMinutes % MINUTES_IN_DAY) + MINUTES_IN_DAY) % MINUTES_IN_DAY
        return TripTime(hour = normalized / 60, minute = normalized % 60)
    }

    companion object {
        const val MAX_HOURS = 23
        private const val MINUTES_IN_DAY = 24 * 60

        val ZERO = TripDuration(hours = 0, minutes = 0)
    }
}
