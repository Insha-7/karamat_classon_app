package com.example.karamat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

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

    }

    private fun getAcademicsData(dayIndex: Int): List<ItemTimeTable> {

        val data = listOf(
            ItemTimeTable("10:10 am", "11:00 am", "DBMS", "lab", "118", "Shubhra Jain"),
            ItemTimeTable("11:10 am", "12:10 pm", "WDA", "theory", "118", "Prashant Singh"),
            ItemTimeTable("2:30 pm", "3:30 pm", "CTP", "theory", "MSTeams", "Bibek Singh"),
            ItemTimeTable("4:00 pm", "5:00 pm", "SPORTS", "class", "Wifi Garden", "Aditya Kumar"),
            ItemTimeTable("5:00 pm", "6:00 pm", "PC", "theory", "GMeet", "Abha Dixit")
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
            ItemTimeTable("10:10 am", "11:00 am", "APP", "lab", "118", "Shubhra Jain"),
            ItemTimeTable("11:10 am", "12:10 pm", "WEB", "theory", "118", "Prashant Singh"),
            ItemTimeTable("2:30 pm", "3:30 pm", "CP", "theory", "MSTeams", "Bibek Singh"),
            ItemTimeTable("4:00 pm", "5:00 pm", "ML", "class", "Wifi Garden", "Aditya Kumar"),
            ItemTimeTable("5:00 pm", "6:00 pm", "WEB3", "theory", "GMeet", "Abha Dixit")
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