package com.example.classon

import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class ItemTimeTable(
    var subject: String? = null,
    var type: String? = null,
    var location: String? = null,
    var teacher: String? = null,
    var dayOfWeek: DayOfWeek? = null,
    var startLocalTime: LocalTime? = null,
    var endLocalTime: LocalTime? = null
) {
    val startTime: String
        get() = startLocalTime?.format(DateTimeFormatter.ofPattern("hh:mm a")) ?: "--"

    val endTime: String
        get() = endLocalTime?.format(DateTimeFormatter.ofPattern("hh:mm a")) ?: "--"
}
