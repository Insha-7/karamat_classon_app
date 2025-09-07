package com.example.classon
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import java.time.DayOfWeek
import java.time.LocalTime

class TimeTableFragment : Fragment(R.layout.fragment_time_table) {

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: ItemTimeTableAdapter

    companion object {
        fun newInstance(dayIndex: Int, source: String): TimeTableFragment {
            val fragment = TimeTableFragment()
            val bundle = Bundle()
            bundle.putInt("dayIndex", dayIndex)
            bundle.putString("source", source)
            fragment.arguments = bundle
            return fragment
        }
    }

    private val timetableListeners = mutableMapOf<String, ListenerRegistration>()

    override fun onDestroyView() {
        super.onDestroyView()
        timetableListeners.values.forEach{it.remove()}
        timetableListeners.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dayIndex = arguments?.getInt("dayIndex") ?: 0
        val source = arguments?.getString("source") ?: "academics"

        android.util.Log.d("TimeTableFragment", "Loading timetable for source: $source, dayIndex: $dayIndex")
        loadTimetableFromFirestore(source, dayIndex, recyclerView)
    }

    private fun loadTimetableFromFirestore(source: String, dayIndex: Int, recyclerView: RecyclerView) {
        val db = FirebaseFirestore.getInstance()
        val days = listOf("MON", "TUE", "WED", "THU", "FRI")
        val dayOfWeekNames = listOf("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY")
        val day = days[dayIndex]
        val dayOfWeekName = dayOfWeekNames[dayIndex]

        android.util.Log.d("TimeTableFragment", "Querying Firestore for: timetables/$source/programs/CS_2024/days/$day/slots")

        timetableListeners[day]?.remove()

        // First, let's check if the source document exists
//        db.collection("timetables")
//            .document(source)
//            .get()
//            .addOnSuccessListener { sourceDoc ->
//                android.util.Log.d("TimeTableFragment", "Source document '$source' exists: ${sourceDoc.exists()}")
//                if (sourceDoc.exists()) {
//                    android.util.Log.d("TimeTableFragment", "Source document data: ${sourceDoc.data}")
//                }
//
//                // Now check if programs collection exists
//                db.collection("timetables")
//                    .document(source)
//                    .collection("programs")
//                    .get()
//                    .addOnSuccessListener { programsSnapshot ->
//                        android.util.Log.d("TimeTableFragment", "Programs collection size: ${programsSnapshot.size()}")
//                        for (doc in programsSnapshot.documents) {
//                            android.util.Log.d("TimeTableFragment", "Program document: ${doc.id}")
//                        }
//
//                        // Now try to get the actual slots
//                        db.collection("timetables")
//                            .document(source)
//                            .collection("programs")
//                            .document("CS_2024")
//                            .collection("days")
//                            .document(day)
//                            .collection("slots")
//                            .get()
//                            .addOnSuccessListener { result ->
//                                android.util.Log.d("TimeTableFragment", "Firestore query successful. Found ${result.size()} documents")
//                                processSlots(result, dayOfWeekName, recyclerView)
//                            }
//                            .addOnFailureListener { e ->
//                                android.util.Log.e("TimeTableFragment", "Firestore query failed", e)
//                                e.printStackTrace()
//                            }
//                    }
//                    .addOnFailureListener { e ->
//                        android.util.Log.e("TimeTableFragment", "Failed to get programs collection", e)
//                    }
//            }
//            .addOnFailureListener { e ->
//                android.util.Log.e("TimeTableFragment", "Failed to get source document", e)
//            }

        val slotsref = db.collection("timetables")
            .document(source)
            .collection("programs")
            .document("CS_2024")
            .collection("days")
            .document(day)
            .collection("slots")

        android.util.Log.d("TimeTableFragment", "Listening to firestore at: $slotsref")

        val listener = slotsref.addSnapshotListener { snapshot, e ->
            if (e != null) {
                android.util.Log.w("TimeTableFragment", "Listen failed.", e)
                return@addSnapshotListener
            }

            if (snapshot != null) {
                android.util.Log.d("TimeTableFragment", "Realtime Update: ${snapshot.size()} docs for $day")
                processSlots(snapshot, dayOfWeekName, recyclerView)
            } else {
                android.util.Log.d("TimeTableFragment", "No data for $day")
            }
        }
        timetableListeners[day] = listener

    }

    private fun processSlots(result: com.google.firebase.firestore.QuerySnapshot, dayOfWeekName: String, recyclerView: RecyclerView) {
        android.util.Log.d("TimeTableFragment", "Processing ${result.size()} slot documents")
        val items = result.mapNotNull { doc ->
            try {
                val startTimeStr = doc.getString("start")
                val endTimeStr = doc.getString("end")
                val subject = doc.getString("subject")
                val teacher = doc.getString("teacher")
                val location = doc.getString("location")
                
                android.util.Log.d("TimeTableFragment", "Document data: subject=$subject, start=$startTimeStr, end=$endTimeStr, teacher=$teacher, location=$location")
                
                if (startTimeStr != null && endTimeStr != null) {
                    ItemTimeTable(
                        subject = subject ?: "",
                        type = doc.getString("type") ?: "",
                        location = location ?: "",
                        teacher = teacher ?: "",
                        dayOfWeek = DayOfWeek.valueOf(dayOfWeekName),
                        startLocalTime = LocalTime.parse(startTimeStr),
                        endLocalTime = LocalTime.parse(endTimeStr)
                    )
                } else {
                    android.util.Log.w("TimeTableFragment", "Missing time data: start=$startTimeStr, end=$endTimeStr")
                    null
                }
            } catch (e: Exception) {
                android.util.Log.e("TimeTableFragment", "Error parsing document: ${doc.id}", e)
                e.printStackTrace()
                null
            }
        }

        android.util.Log.d("TimeTableFragment", "Created ${items.size} timetable items")
        
        if (items.isEmpty()) {
            android.util.Log.d("TimeTableFragment", "No timetable data found")
            // Create a placeholder item to show "No classes today"
            val placeholderItems = listOf(
                ItemTimeTable(
                    subject = "No classes scheduled",
                    type = "",
                    location = "",
                    teacher = "",
                    dayOfWeek = DayOfWeek.valueOf(dayOfWeekName),
                    startLocalTime = null,
                    endLocalTime = null
                )
            )
            recyclerView.adapter = ItemTimeTableAdapter(placeholderItems)
        } else {
            recyclerView.adapter = ItemTimeTableAdapter(items)
            NotificationScheduler.scheduleAll(requireContext(), items)
        }
    }




}