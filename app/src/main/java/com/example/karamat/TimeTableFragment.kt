package com.example.karamat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class TimeTableFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ItemTimeTableAdapter

    companion object {
        private const val ARG_DAY = "day"

        fun newInstance(day: String): TimeTableFragment {
            val fragment = TimeTableFragment()
            val args = Bundle()
            args.putString(ARG_DAY, day)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_time_table, container, false)

        recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val day = arguments?.getString(ARG_DAY) ?: ""
        recyclerView.adapter = ItemTimeTableAdapter(getDataForDay(day))







        return view

    }

    private fun getDataForDay(day: String): List<ItemTimeTable> {

        val data = listOf(
            ItemTimeTable("10:10 am", "11:00 am", "DBMS", "lab", "118", "Shubhra Jain"),
            ItemTimeTable("11:10 am", "12:10 pm", "WDA", "theory", "118", "Prashant Singh"),
            ItemTimeTable("2:30 pm", "3:30 pm", "CTP", "theory", "MSTeams", "Bibek Singh"),
            ItemTimeTable("4:00 pm", "5:00 pm", "SPORTS", "class", "Wifi Garden", "Aditya Kumar"),
            ItemTimeTable("5:00 pm", "6:00 pm", "PC", "theory", "GMeet", "Abha Dixit")
        )

        return when (day) {
            "Monday" -> data
            "Tuesday" -> data
            "Wednesday" -> data
            "Thursday" -> data
            "Friday" -> data
            else -> emptyList()
        }
    }

}