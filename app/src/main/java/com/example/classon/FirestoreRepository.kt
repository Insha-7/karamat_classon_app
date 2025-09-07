package com.example.classon

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

object FirestoreRepository {

    private val db = FirebaseFirestore.getInstance()

    suspend fun fetchAcademicsAndAxiosContext(): String {
        val sources = listOf("academics", "axios")
        val sb = StringBuilder()

        val today = LocalDate.now()
            .dayOfWeek
            .getDisplayName(TextStyle.FULL, Locale.ENGLISH)
            .uppercase()

        sb.appendLine("Today is $today.")
        sb.appendLine("Here is the timetable data for all days:\n")

        for (source in sources) {
            val daysSnapshot = db.collection("timetables")
                .document(source)
                .collection("programs")
                .document("CS_2024")
                .collection("days")
                .get()
                .await()

            sb.appendLine("=== $source Timetable ===")

            for (dayDoc in daysSnapshot) {
                sb.appendLine("Day: ${dayDoc.id}")
                val slotsSnapshot = dayDoc.reference.collection("slots").get().await()
                for (slot in slotsSnapshot) {
                    val subject = slot.getString("subject") ?: "Unknown"
                    val teacher = slot.getString("teacher") ?: "Unknown"
                    val start = slot.getString("start") ?: "??"
                    val end = slot.getString("end") ?: "??"
                    sb.appendLine(" - $start–$end: $subject ($teacher)")
                }
            }
            sb.appendLine()
        }

        return sb.toString()
    }
}