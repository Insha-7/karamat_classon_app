package com.example.classon

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

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

    }
}