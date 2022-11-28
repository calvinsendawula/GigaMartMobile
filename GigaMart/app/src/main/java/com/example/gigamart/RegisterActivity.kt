package com.example.gigamart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth

        val signInClick = findViewById<TextView>(R.id.linkSignIn)
        signInClick.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        val registerButton: Button = findViewById(R.id.btnRegister2)
        registerButton.setOnClickListener {
            performSignup()
        }
    }

    private fun performSignup() {
        val email = findViewById<EditText>(R.id.editEmailAddress2).text.toString()
        val password = findViewById<EditText>(R.id.editPassword2).text.toString()
        val confirmPassword = findViewById<EditText>(R.id.editConfirmPassword1).text.toString()

        /*if (email.isEmpty() || password.isEmpty()) {
            return
        }*/

        if (password != confirmPassword) {
            Toast.makeText(this, "Password and Confirm password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        } else{
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success,more to the next activity ie main activity

                        val intent = Intent(this, AccountActivity::class.java)
                        startActivity(intent)

                        Toast.makeText(
                            baseContext, "Success.",
                            Toast.LENGTH_SHORT).show()
                    } else {
                        // If sign in fails, display a message to the user.
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener{
                    Toast.makeText(this, "Error registering ${it.localizedMessage}", Toast.LENGTH_SHORT)
                        .show()
                }
        }
    }


}