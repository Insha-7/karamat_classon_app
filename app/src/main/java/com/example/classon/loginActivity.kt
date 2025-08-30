package com.example.classon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnsignin = findViewById<Button>(R.id.btnsignin)
        btnsignin.setOnClickListener {
            Intent(this,HomeActivity::class.java).also{
                startActivity(it)
            }
        }

        val textsignup: TextView = findViewById(R.id.textsignup)
        textsignup.setOnClickListener {
            Intent(this,SignupActivity::class.java).also{
                startActivity(it)
            }
        }
    }
}