package com.example.karamat

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import java.time.LocalTime

class EventFragment : Fragment(R.layout.fragment_event) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val events = listOf(
            Event(
                title = "Zephyr dance",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                time = LocalTime.of(23, 59),
                venue = "Reception",
                type = "Zephyr"
            ),
            Event(
                title = "Estrella song",
                date = LocalDate.of(2025, 9, 5),
                time = LocalTime.of(10, 0),
                venue = "Room 527",
                type = "Estrella"
            ),
            Event(
                title = "Goonj Act",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                time = LocalTime.of(23, 59),
                venue = "Reception",
                type = "Goonj"
            ),
            Event(
                title = "Laddu Fauding comp",
                date = LocalDate.of(2025, 9, 5),
                time = LocalTime.of(10, 0),
                venue = "H2 mess",
                type = "Goonj"
            ),
            Event(
                title = "Badminton Singles",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                time = LocalTime.of(23, 59),
                venue = "Badminton Court",
                type = "Eifer"
            ),
            Event(
                title = "Seraphim meet",
                date = LocalDate.of(2025, 9, 5),
                time = LocalTime.of(10, 0),
                venue = "To be declared",
                type = "Seraphim"
            ),
            Event(
                title = "Utkrisht meet",
                date = LocalDate.of(2025, 9, 2),   // LocalDate instead of string
                time = LocalTime.of(23, 59),
                venue = "Room 101",
                type = "Utkrisht"
            ),
            Event(
                title = "Crotonia presentation",
                date = LocalDate.of(2025, 9, 5),
                time = LocalTime.of(10, 0),
                venue = "Room 120",
                type = "Crotonia"
            )


        )


        // Bind RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = EventAdapter(events)

    }

}