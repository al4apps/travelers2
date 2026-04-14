package com.al4apps.travelers2.features.search_form.calendar.model

enum class CalendarMonth(val number: Int, val title: String) {
    JANUARY(1, "Январь"),
    FEBRUARY(2, "Февраль"),
    MARCH(3, "Март"),
    APRIL(4, "Апрель"),
    MAY(5, "Май"),
    JUNE(6, "Июнь"),
    JULY(7, "Июль"),
    AUGUST(8, "Август"),
    SEPTEMBER(9, "Сентябрь"),
    OCTOBER(10, "Октябрь"),
    NOVEMBER(11, "Ноябрь"),
    DECEMBER(12, "Декабрь"),
    ;

    companion object {
        fun byNumber(number: Int): CalendarMonth = entries.first { it.number == number }
    }
}
