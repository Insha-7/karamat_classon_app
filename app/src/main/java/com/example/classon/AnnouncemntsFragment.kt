package com.example.classon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.LocalTime

class AnnouncemntsFragment : Fragment(R.layout.fragment_announcemnts) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val announcements = listOf(
            Announcement(
                title = "DBMS Assignment",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                endTime = LocalTime.of(23, 59),
                description = "Submit PDF on LMS",
                type = "assignment"
            ),
            Announcement(
                title = "OS Lab Test",
                date = LocalDate.of(2025, 9, 5),
                startTime = LocalTime.of(10,0),
                endTime = LocalTime.of(12,0),
                description = "Syllabus: Process Scheduling",
                type = "exam"
            ),
            Announcement(
                title = "DBMS Assignment",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                endTime = LocalTime.of(23, 59),
                description = "Submit PDF on LMS",
                type = "assignment"
            ),
            Announcement(
                title = "OS Lab Test",
                date = LocalDate.of(2025, 9, 5),
                startTime = LocalTime.of(10,0),
                endTime = LocalTime.of(12,0),
                description = "Syllabus: Process Scheduling",
                type = "exam"
            ),
            Announcement(
                title = "DBMS Assignment",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                endTime = LocalTime.of(23, 59),
                description = "Submit PDF on LMS",
                type = "assignment"
            ),
            Announcement(
                title = "OS Lab Test",
                date = LocalDate.of(2025, 9, 5),
                startTime = LocalTime.of(10,0),
                endTime = LocalTime.of(12,0),
                description = "Syllabus: Process Scheduling",
                type = "exam"
            ),
            Announcement(
                title = "DBMS Assignment",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                endTime = LocalTime.of(23, 59),
                description = "Submit PDF on LMS",
                type = "assignment"
            ),
            Announcement(
                title = "OS Lab Test",
                date = LocalDate.of(2025, 9, 5),
                startTime = LocalTime.of(10,0),
                endTime = LocalTime.of(12,0),
                description = "Syllabus: Process Scheduling",
                type = "exam"
            )


        )

        // Bind RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = AnnouncementAdapter(announcements)

    }
}