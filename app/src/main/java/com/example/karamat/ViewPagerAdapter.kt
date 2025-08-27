package com.example.karamat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

        private val days = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday")

        override fun getItemCount(): Int = days.size

        override fun createFragment(position: Int): Fragment {
            return TimeTableFragment.newInstance(days[position])
        }
    }

