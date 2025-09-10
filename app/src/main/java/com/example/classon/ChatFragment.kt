package com.example.classon

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

class ChatFragment : Fragment(R.layout.fragment_chat) {
    private lateinit var chatadapter: ChatAdapter
    private val messages = mutableListOf<ChatMessage>()

    override fun onResume() {
        super.onResume()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.GONE
        requireActivity().findViewById<FloatingActionButton>(R.id.geminiButton).visibility = View.GONE

    }

    override fun onStop() {
        super.onStop()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView).visibility = View.VISIBLE
        requireActivity().findViewById<FloatingActionButton>(R.id.geminiButton).visibility = View.VISIBLE
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.chatRecyclerView)
        chatadapter = ChatAdapter(messages)
        recyclerView.adapter = chatadapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val sendButton = view.findViewById<ImageButton>(R.id.sendButton)
        val userInput = view.findViewById<EditText>(R.id.userInput)

        sendButton.setOnClickListener {
            val text = userInput.text.toString()
            if (text.isNotEmpty()) {
                val message = ChatMessage(text, isUser = true)
                messages.add(message)
                chatadapter.notifyItemInserted(messages.size - 1)
                messages.add(ChatMessage("", isTyping = true))
                chatadapter.notifyItemInserted(messages.size - 1)
                recyclerView.scrollToPosition(messages.size - 1)
                userInput.text.clear()

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val contextText = FirestoreRepository.fetchAcademicsAndAxiosContext()

                        val isTimetableQuery = text.contains("timetable", ignoreCase = true) ||
                                text.contains("class", ignoreCase = true) ||
                                text.contains("schedule", ignoreCase = true) ||
                                text.contains("free time", ignoreCase = true)

                        val prompt = buildString {
                            if (isTimetableQuery) {
                                append("You are a university timetable assistant. ")
                                append("Rules for answering:\n")
                                append("1. Only answer using the timetable context given.\n")
                                append("2. If the user asks for a specific day (e.g., tomorrow, Friday), show ONLY that day’s timetable.\n")
                                append("3. Format answers in a clear and readable way (use bullet points or line breaks).\n")
                                append("4. If asked about 'free time', compute gaps longer than 1 hour between classes.\n")
                                append("5. Show free time before first class and after last class too.\n")
                                append("6. Be concise, but always complete.\n\n")
                                append("7. If the user asks 'when is my next class' or similar, calculate it based on the current time provided.\n")
                                append("8. Keep the answers natural.")
                                append("Tone instructions:\n")
                                append(" - Respond naturally and conversationally.\n")
                                append(" - You can use friendly phrasing, like 'Looks like your next class is...' or 'You have... coming up soon!'\n")
                                append(" - Avoid repeating the day if it is already implied.\n")
                                append(" - Keep it concise but supportive and student-friendly.\n\n")
                                append("When answering, feel free to use casual phrases like 'Looks like', 'You have', 'Your next class is', etc. Format using short sentences or bullet points if helpful.\n")



                                append("=== TIMETABLE CONTEXT START ===\n")
                                append(contextText)
                                append("\n=== TIMETABLE CONTEXT END ===\n\n")

                                append("Now answer the user's question:\n")
                                append(text)
                            }else {
                                append("You are a helpful AI assistant for students. ")
                                append("=== TIMETABLE CONTEXT START ===\n")
                                append(contextText)
                                append("\n=== TIMETABLE CONTEXT END ===\n\n")
                                append("Answer this question naturally and supportively also if possible take reference from the context provided:")
                                append(text)
                            }
                        }


                        val answer = GeminiRepository.askGemini(prompt)

                        withContext(Dispatchers.Main) {
                            if (messages.lastOrNull()?.isTyping == true) {
                                messages.removeAt(messages.size - 1)
                                chatadapter.notifyItemRemoved(messages.size)
                            }

                            messages.add(ChatMessage(answer, isUser = false))
                            chatadapter.notifyItemInserted(messages.size - 1)
                            recyclerView.scrollToPosition(messages.size - 1)
                        }
                    } catch (t: Throwable) {
                        t.printStackTrace()
                        withContext(Dispatchers.Main) {
                            if (messages.lastOrNull()?.isTyping == true) {
                                messages.removeAt(messages.size - 1)
                                chatadapter.notifyItemRemoved(messages.size)
                            }
                            messages.add(ChatMessage("⚠️ Something went wrong while connecting AI.", isUser = false))
                            chatadapter.notifyItemInserted(messages.size - 1)
                        }
                    }
                }
            }
        }
    }
}
