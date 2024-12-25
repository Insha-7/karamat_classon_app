package com.example.karamat

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val textsignin: TextView = findViewById(R.id.textsignin)
        textsignin.setOnClickListener {
            Intent(this,LoginActivity::class.java).also{
                startActivity(it)
            }
        }

        val btnsignup = findViewById<Button>(R.id.btnsignup)
        btnsignup.setOnClickListener {
            Intent(this,HomeActivity::class.java).also{
                startActivity(it)
            }
        }
    }

}