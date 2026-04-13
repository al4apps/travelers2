package com.al4apps.travelers2.features.search_trips.calendar.map

import com.al4apps.travelers2.core.model.TripDate

fun TripDate.isBefore(other: TripDate): Boolean =
    when {
        year != other.year -> year < other.year
        month != other.month -> month < other.month
        else -> day < other.day
    }

fun isBeforeMonth(
    year: Int,
    month: Int,
    minYear: Int,
    minMonth: Int,
): Boolean =
    when {
        year != minYear -> year < minYear
        else -> month < minMonth
    }
