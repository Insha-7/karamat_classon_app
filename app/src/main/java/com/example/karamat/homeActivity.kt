package com.example.karamat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val data = listOf(
            ItemTimeTable("10:10 am", "11:00 am", "DBMS", "lab", "118", "Shubhra Jain"),
            ItemTimeTable("11:10 am", "12:10 pm", "WDA", "theory", "118", "Prashant Singh"),
            ItemTimeTable("2:30 pm", "3:30 pm", "CTP", "theory", "MSTeams", "Bibek Singh"),
            ItemTimeTable("4:00 pm", "5:00 pm", "SPORTS", "class", "Wifi Garden", "Aditya Kumar"),
            ItemTimeTable("5:00 pm", "6:00 pm", "PC", "theory", "GMeet", "Abha Dixit")
        )

        recyclerView.adapter = ItemTimeTableAdapter(data)

    }



}