package com.example.classon

import java.time.LocalDate
import java.time.LocalTime

data class Event(
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
    val venue: String,
    val type: String // "zephyr", "estrella", etc.
)