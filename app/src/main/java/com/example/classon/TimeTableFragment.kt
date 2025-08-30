package com.example.classon

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.time.DayOfWeek
import java.time.LocalTime

class TimeTableFragment : Fragment(R.layout.fragment_time_table) {

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: ItemTimeTableAdapter

    companion object {
//        private const val ARG_DAY = "day"

        fun newInstance(dayIndex: Int, source: String): TimeTableFragment {
            val fragment = TimeTableFragment()
            val bundle = Bundle()
            bundle.putInt("dayIndex", dayIndex)
            bundle.putString("source", source)
            fragment.arguments = bundle
            return fragment
        }
    }


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        val view =  inflater.inflate(R.layout.fragment_time_table, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dayIndex = arguments?.getInt("dayIndex") ?: 0
        val source = arguments?.getString("source") ?: "academics"

        val timetableData = when (source) {
            "academics" -> getAcademicsData(dayIndex)
            "axios" -> getAxiosData(dayIndex)
            else -> emptyList()
        }

        recyclerView.adapter = ItemTimeTableAdapter(timetableData)

        NotificationScheduler.scheduleAll(requireContext(), timetableData)

    }

    private fun getAcademicsData(dayIndex: Int): List<ItemTimeTable> {

        val data = listOf(
            ItemTimeTable("DBMS", "lab", "118", "Shubhra Jain",
                DayOfWeek.MONDAY, LocalTime.of(10, 10), LocalTime.of(11, 0)),
            ItemTimeTable("WDA", "theory", "118", "Prashant Singh",
                DayOfWeek.MONDAY, LocalTime.of(11, 10), LocalTime.of(12, 10)),
            ItemTimeTable("CTP", "theory", "MSTeams", "Bibek Singh",
                DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)),
            ItemTimeTable("SPORTS", "class", "Wifi Garden", "Aditya Kumar",
                DayOfWeek.MONDAY, LocalTime.of(15, 40), LocalTime.of(17, 0)),
            ItemTimeTable("PC", "theory", "GMeet", "Abha Dixit",
                DayOfWeek.MONDAY, LocalTime.of(15, 50), LocalTime.of(18, 0))
        )

        return when (dayIndex) {
            0 -> data
            1 -> data
            2 -> data
            3 -> data
            4 -> data
            else -> emptyList()
        }
    }

    private fun getAxiosData(dayIndex: Int): List<ItemTimeTable> {

        val data = listOf(
            ItemTimeTable("APP", "lab", "118", "Shubhra Jain",
                DayOfWeek.MONDAY, LocalTime.of(10, 10), LocalTime.of(11, 0)),
            ItemTimeTable("WEB", "theory", "118", "Prashant Singh",
                DayOfWeek.MONDAY, LocalTime.of(11, 10), LocalTime.of(12, 10)),
            ItemTimeTable("CP", "theory", "MSTeams", "Bibek Singh",
                DayOfWeek.MONDAY, LocalTime.of(14, 30), LocalTime.of(15, 30)),
            ItemTimeTable("ML", "class", "Wifi Garden", "Aditya Kumar",
                DayOfWeek.MONDAY, LocalTime.of(15, 35), LocalTime.of(17, 0)),
            ItemTimeTable("WEB3", "theory", "GMeet", "Abha Dixit",
                DayOfWeek.MONDAY, LocalTime.of(15, 55), LocalTime.of(18, 0))
        )

        return when (dayIndex) {
            0 -> data
            1 -> data
            2 -> data
            3 -> data
            4 -> data
            else -> emptyList()
        }
    }



}