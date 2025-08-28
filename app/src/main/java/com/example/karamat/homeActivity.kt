package com.example.karamat

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val viewPager = findViewById<ViewPager2>(R.id.viewPager)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)

        // Adapter with Fragments for each day
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri")

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = days[position]
        }.attach()

    }



}