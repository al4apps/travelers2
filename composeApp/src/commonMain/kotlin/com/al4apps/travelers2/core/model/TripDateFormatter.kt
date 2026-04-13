package com.al4apps.travelers2.core.model

object TripDateFormatter {
    fun toDisplayString(date: TripDate): String {
        val day = date.day.toString().padStart(length = 2, padChar = '0')
        val month = date.month.toString().padStart(length = 2, padChar = '0')
        return "$day.$month.${date.year}"
    }

    fun parseOrNull(value: String): TripDate? {
        val parts = value.split(".")
        if (parts.size != 3) return null

        val day = parts[0].toIntOrNull() ?: return null
        val month = parts[1].toIntOrNull() ?: return null
        val year = parts[2].toIntOrNull() ?: return null

        if (month !in 1..12) return null
        val maxDay = daysInMonth(year = year, month = month)
        if (day !in 1..maxDay) return null

        return TripDate(year = year, month = month, day = day)
    }

    private fun daysInMonth(year: Int, month: Int): Int =
        when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (isLeapYear(year)) 29 else 28
            else -> 30
        }

    private fun isLeapYear(year: Int): Boolean = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}
