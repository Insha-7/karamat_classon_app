package com.example.classon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<ChatMessage>) :
 RecyclerView.Adapter<ChatAdapter.MessageViewHolder>() {
     companion object{
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_GEMINI = 2
     }

     override fun getItemViewType(position: Int): Int {
         return if (messages[position].isUser) {
             VIEW_TYPE_USER
         } else {
             VIEW_TYPE_GEMINI
         }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutId = if (viewType == VIEW_TYPE_USER) {
            R.layout.item_user_message
        } else {
            R.layout.item_gemini_message
        }

        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.bind(message)
    }

    override fun getItemCount(): Int {
        return messages.size
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val messageText: TextView = itemView.findViewById(R.id.messageText)
        fun bind(message: ChatMessage) {
            messageText.text = message.text
        }
    }
 }