package com.example.classon

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {

    private var backPressedTime: Long = 0
    private var toast: Toast? = null

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.top_app_bar_menu, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<MaterialToolbar>(R.id.topAppBar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        toolbar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.logout -> {
                    signOut()
                    true
                }
                else -> false
            }
        }

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

    private fun signOut() {
        // Firebase sign-out
        FirebaseAuth.getInstance().signOut()

        // Google sign-out
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        googleSignInClient.signOut().addOnCompleteListener {
            // Now go back to LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

}
