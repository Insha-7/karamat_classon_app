package com.example.karamat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity, private val source: String) : FragmentStateAdapter(activity) {

//        private val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

        override fun getItemCount(): Int = 5

        override fun createFragment(position: Int): Fragment {
            return TimeTableFragment.newInstance(position, source)
        }
    }

