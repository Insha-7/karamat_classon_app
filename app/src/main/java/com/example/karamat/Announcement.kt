package com.example.karamat

import java.time.LocalDate
import java.time.LocalTime

data class Announcement(
    val title: String,
    val date: LocalDate,
    val startTime: LocalTime? = null,
    val endTime: LocalTime? = null,
    val description: String? = null,
    val type: String // "assignment", "exam", etc.
)
