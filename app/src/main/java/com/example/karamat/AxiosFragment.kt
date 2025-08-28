package com.example.karamat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AxiosFragment : Fragment(R.layout.fragment_axios) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = view.findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)

        // Adapter with Fragments for each day
        val adapter = ViewPagerAdapter(requireActivity(), "axios")
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")
            tab.text = days[position]
        }.attach()

    }

}