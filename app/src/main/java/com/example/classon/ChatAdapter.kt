package com.example.classon

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ChatAdapter(private val messages: List<ChatMessage>) :
 RecyclerView.Adapter<RecyclerView.ViewHolder>() {
     companion object{
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_GEMINI = 2
         private const val VIEW_TYPE_TYPING = 3
     }

    inner class TypingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dot1: View = itemView.findViewById(R.id.dot1)
        val dot2: View = itemView.findViewById(R.id.dot2)
        val dot3: View = itemView.findViewById(R.id.dot3)
    }

    override fun getItemViewType(position: Int): Int {
        val msg = messages[position]
        return when {
            msg.isTyping -> VIEW_TYPE_TYPING
            msg.isUser -> VIEW_TYPE_USER
            else -> VIEW_TYPE_GEMINI
        }
     }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_USER -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_message, parent, false)
                MessageViewHolder(view)
            }
            VIEW_TYPE_GEMINI -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_gemini_message, parent, false)
                MessageViewHolder(view)
            }
            VIEW_TYPE_TYPING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_typing_indicator, parent, false)
                TypingViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageViewHolder -> {
                holder.bind(messages[position])
            }
            is TypingViewHolder -> {
                animateDots(holder.dot1, holder.dot2, holder.dot3)
            }
        }
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

    private fun animateDots(dot1: View, dot2: View, dot3: View) {
        val animDuration = 500L

        val animator1 = ObjectAnimator.ofFloat(dot1, "alpha", 0f, 1f).apply {
            duration = animDuration
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }

        val animator2 = ObjectAnimator.ofFloat(dot2, "alpha", 0f, 1f).apply {
            duration = animDuration
            startDelay = 200
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }

        val animator3 = ObjectAnimator.ofFloat(dot3, "alpha", 0f, 1f).apply {
            duration = animDuration
            startDelay = 400
            repeatMode = ValueAnimator.REVERSE
            repeatCount = ValueAnimator.INFINITE
        }

        animator1.start()
        animator2.start()
        animator3.start()
    }


}