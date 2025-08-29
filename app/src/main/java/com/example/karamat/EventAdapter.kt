package com.example.karamat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class EventAdapter(private val events : List<Event>) :
    RecyclerView.Adapter<EventAdapter.EventViewHolder>() {

     inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.eventTitle)
        val date: TextView = itemView.findViewById(R.id.eventDate)
        val time: TextView = itemView.findViewById(R.id.eventTime)
        val venue: TextView = itemView.findViewById(R.id.eventVenue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        val context = holder.itemView.context
        val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")

        holder.title.text = event.title

        holder.date.text = context.getString(
            R.string.date,
            event.date.format(dateFormatter)
        )

        holder.time.text = context.getString(
            R.string.time,
            event.time.format(timeFormatter)
        )

        holder.venue.text = context.getString(R.string.venue, event.venue)


    }

    override fun getItemCount() = events.size




    }