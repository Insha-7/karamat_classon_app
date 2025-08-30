package com.example.karamat

import java.time.DayOfWeek
import java.time.LocalTime
import java.time.format.DateTimeFormatter

data class ItemTimeTable(
    var subject: String,
    var type: String,
    var location: String,
    var teacher: String,
    var dayOfWeek: DayOfWeek,
    var startLocalTime: LocalTime,
    var endLocalTime: LocalTime)
    {
        // Computed properties for UI display
        val startTime: String
        get() = startLocalTime.format(DateTimeFormatter.ofPattern("hh:mm a"))

        val endTime: String
        get() = endLocalTime.format(DateTimeFormatter.ofPattern("hh:mm a"))
    }


