package com.example.classon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import java.time.DayOfWeek
import java.time.LocalDate

class AcademicsFragment : Fragment(R.layout.fragment_academics) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        // Adapter with Fragments for each day
        val adapter = ViewPagerAdapter(requireActivity(), "academics")
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
            tab.text = days[position]
        }.attach()

        val today = LocalDate.now().dayOfWeek
        val todayIndex = when (today) {
            DayOfWeek.MONDAY -> 0
            DayOfWeek.TUESDAY -> 1
            DayOfWeek.WEDNESDAY -> 2
            DayOfWeek.THURSDAY -> 3
            DayOfWeek.FRIDAY -> 4
            else -> 0 // fallback for Sat/Sun
        }
        viewPager.setCurrentItem(todayIndex, false)

    }
}