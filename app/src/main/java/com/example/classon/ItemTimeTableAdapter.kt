package com.example.classon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemTimeTableAdapter(
    private val items: List<ItemTimeTable>
) : RecyclerView.Adapter<ItemTimeTableAdapter.ItemTimeTableViewHolder>() {

    inner class ItemTimeTableViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val startTime: TextView = itemView.findViewById(R.id.startTime)
        val endTime: TextView = itemView.findViewById(R.id.endTime)
        val subject: TextView = itemView.findViewById(R.id.subject)
        val type: TextView = itemView.findViewById(R.id.type)
        val location: TextView = itemView.findViewById(R.id.location)
        val teacher: TextView = itemView.findViewById(R.id.teacher)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemTimeTableViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_timetable, parent, false)
        return ItemTimeTableViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemTimeTableViewHolder, position: Int) {
        val item = items[position]
        holder.startTime.text = item.startTime
        holder.endTime.text = item.endTime
        holder.subject.text = item.subject
        holder.type.text = item.type
        holder.location.text = item.location
        holder.teacher.text = item.teacher
    }

    override fun getItemCount(): Int {
        return items.size
    }
}