package com.al4apps.travelers2.features.search_trips.calendar.map

import com.al4apps.travelers2.features.search_trips.model.CalendarDate

object CalendarDateFormatter {
    fun toViewString(date: CalendarDate): String {
        val day = date.day.toString().padStart(length = 2, padChar = '0')
        val month = date.month.toString().padStart(length = 2, padChar = '0')
        return "$day.$month.${date.year}"
    }

    fun parseOrNull(value: String): CalendarDate? {
        val parts = value.split(".")
        if (parts.size != 3) return null

        val day = parts[0].toIntOrNull() ?: return null
        val month = parts[1].toIntOrNull() ?: return null
        val year = parts[2].toIntOrNull() ?: return null

        if (month !in 1..12) return null
        val maxDay = CalendarMath.daysInMonth(year = year, month = month)
        if (day !in 1..maxDay) return null

        return CalendarDate(
            year = year,
            month = month,
            day = day,
        )
    }
}
