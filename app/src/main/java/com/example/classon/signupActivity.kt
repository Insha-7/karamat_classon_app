package com.example.classon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class SignupActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        val nameEt = findViewById<EditText>(R.id.editTextText)
        val emailEt = findViewById<EditText>(R.id.editTextTextEmailAddress2)
        val passwordEt = findViewById<EditText>(R.id.editTextTextPassword2)
        val confirmPasswordEt = findViewById<EditText>(R.id.editTextTextPassword3)

        val textsignin: TextView = findViewById(R.id.textsignin)

        textsignin.setOnClickListener {
            Intent(this,LoginActivity::class.java).also{
                startActivity(it)
            }
        }

        val btnsignup = findViewById<Button>(R.id.btnsignup)
        btnsignup.setOnClickListener {

            val name = nameEt.text.toString().trim()
            val email = emailEt.text.toString().trim()
            val password = passwordEt.text.toString().trim()
            val confirmPassword = confirmPasswordEt.text.toString().trim()

            // Validations
            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                showToast("All fields are required")
                return@setOnClickListener
            }

            if (!email.endsWith("@iiitl.ac.in")) {
                showToast("Please use your IIITL email ID")
                return@setOnClickListener
            }

            if (password.length < 8) {
                showToast("Password must be at least 8 characters")
                return@setOnClickListener
            }

            if (password != confirmPassword) {
                showToast("Passwords do not match")
                return@setOnClickListener
            }

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task: com.google.android.gms.tasks.Task<com.google.firebase.auth.AuthResult> ->
                    if (task.isSuccessful) {
                        showToast("Signup successful!")
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        showToast("Signup failed: ${task.exception?.message}")
                    }
                }

        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }


}