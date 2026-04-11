package com.al4apps.travelers2.features.search_trips.calendar.map

object CalendarMath {
    fun daysInMonth(year: Int, month: Int): Int =
        when (month) {
            1, 3, 5, 7, 8, 10, 12 -> 31
            4, 6, 9, 11 -> 30
            2 -> if (isLeapYear(year)) 29 else 28
            else -> 30
        }

    fun dayOfWeekMondayBased(year: Int, month: Int, day: Int): Int {
        // Sakamoto algorithm: 0=Sunday..6=Saturday, convert to Monday-first.
        val offsets = intArrayOf(0, 3, 2, 5, 0, 3, 5, 1, 4, 6, 2, 4)
        var adjustedYear = year
        if (month < 3) adjustedYear -= 1
        val sundayBased =
            (adjustedYear + adjustedYear / 4 - adjustedYear / 100 + adjustedYear / 400 + offsets[month - 1] + day) % 7
        return (sundayBased + 6) % 7
    }

    private fun isLeapYear(year: Int): Boolean = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}
