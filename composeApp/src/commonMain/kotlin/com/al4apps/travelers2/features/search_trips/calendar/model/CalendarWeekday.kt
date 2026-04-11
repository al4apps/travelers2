package com.al4apps.travelers2.features.search_trips.calendar.model

import org.jetbrains.compose.resources.StringResource
import travelers2.composeapp.generated.resources.Res
import travelers2.composeapp.generated.resources.calendar_weekday_fri
import travelers2.composeapp.generated.resources.calendar_weekday_mon
import travelers2.composeapp.generated.resources.calendar_weekday_sat
import travelers2.composeapp.generated.resources.calendar_weekday_sun
import travelers2.composeapp.generated.resources.calendar_weekday_thu
import travelers2.composeapp.generated.resources.calendar_weekday_tue
import travelers2.composeapp.generated.resources.calendar_weekday_wed

enum class CalendarWeekday(val titleRes: StringResource) {
    MONDAY(Res.string.calendar_weekday_mon),
    TUESDAY(Res.string.calendar_weekday_tue),
    WEDNESDAY(Res.string.calendar_weekday_wed),
    THURSDAY(Res.string.calendar_weekday_thu),
    FRIDAY(Res.string.calendar_weekday_fri),
    SATURDAY(Res.string.calendar_weekday_sat),
    SUNDAY(Res.string.calendar_weekday_sun),
}
