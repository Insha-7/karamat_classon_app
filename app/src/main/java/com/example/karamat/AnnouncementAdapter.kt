package com.example.karamat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.format.DateTimeFormatter

class AnnouncementAdapter(private val announcements: List<Announcement>) :
    RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>() {

    inner class AnnouncementViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.announcementTitle)
        val date: TextView = itemView.findViewById(R.id.announcementDate)
        val startTime: TextView = itemView.findViewById(R.id.announcementStartTime)
        val endTime: TextView = itemView.findViewById(R.id.announcementEndTime)
        val description: TextView = itemView.findViewById(R.id.announcementDescription)
        val detailsLayout: LinearLayout = itemView.findViewById(R.id.detailsLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnnouncementViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_announcement_card, parent, false)
        return AnnouncementViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnnouncementViewHolder, position: Int) {
        val announcement = announcements[position]

        val context = holder.itemView.context
        val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
        val timeFormatter = DateTimeFormatter.ofPattern("hh:mm a")

        holder.title.text = announcement.title

        // ✅ Use string resources instead of concatenation
        holder.date.text = context.getString(
            R.string.date,
            announcement.date.format(dateFormatter)
        )

        // Start time (hidden for assignments)
        if (announcement.type == "assignment") {
            holder.startTime.visibility = View.GONE
        } else {
            announcement.startTime?.let {
                holder.startTime.text = context.getString(
                    R.string.announcement_start_time,
                    it.format(timeFormatter)
                )
            }
//                holder.startTime.visibility = View.VISIBLE
//            } ?: run { holder.startTime.visibility = View.GONE }
        }

        // End time
        announcement.endTime?.let {
            holder.endTime.text = context.getString(
                R.string.announcement_end_time,
                it.format(timeFormatter)
            )
        }
//            holder.endTime.visibility = View.VISIBLE
//        } ?: run { holder.endTime.visibility = View.GONE }

        // Description
        if (!announcement.description.isNullOrEmpty()) {
            holder.description.text = announcement.description
            holder.description.visibility = View.VISIBLE
        } else {
            holder.description.visibility = View.GONE
        }

        // Expand/Collapse toggle
        holder.itemView.setOnClickListener {
            holder.detailsLayout.visibility =
                if (holder.detailsLayout.visibility == View.VISIBLE) View.GONE
                else View.VISIBLE
        }
    }

    override fun getItemCount() = announcements.size
}
