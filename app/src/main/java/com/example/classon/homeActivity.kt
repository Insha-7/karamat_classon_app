package com.example.classon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        loadFragment(AcademicsFragment())

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.acads -> {
                    loadFragment(AcademicsFragment())
                    true
                }
                R.id.axios -> {
                    loadFragment(AxiosFragment())
                    true
                }
                R.id.announcement -> {
                    loadFragment(AnnouncemntsFragment())
                    true
                }
                R.id.cultural -> {
                    loadFragment(EventFragment())
                    true
                }
                else -> false
            }
        }


    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }



}