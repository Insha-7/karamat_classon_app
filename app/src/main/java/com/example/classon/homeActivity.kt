package com.example.classon

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class HomeActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var toast: Toast? = null

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

        val geminiButton: FloatingActionButton = findViewById(R.id.geminiButton)

        geminiButton.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, ChatFragment())
                .addToBackStack("chat")
                .commit()
        }

        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (supportFragmentManager.backStackEntryCount > 0) {
                    supportFragmentManager.popBackStack()
                } else {
                    if (backPressedTime + 2000 > System.currentTimeMillis()) {
                        toast?.cancel()
                        finish() // exits activity
                    } else {
                        toast = Toast.makeText(
                            this@HomeActivity,
                            "Press back again to exit",
                            Toast.LENGTH_SHORT
                        )
                        toast?.show()
                    }
                    backPressedTime = System.currentTimeMillis()
                }
            }
        })
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment)
            .commit()
    }
}
