package com.example.gigamart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = Firebase.auth

        val loginClick = findViewById<Button>(R.id.btnLogin)
        loginClick.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }

        val signUpClick = findViewById<TextView>(R.id.linkSignUp)
        signUpClick.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        val forgotClick = findViewById<Button>(R.id.btnForgotPassword)
        forgotClick.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.btnLogin)
        loginButton.setOnClickListener{
            performLogin()
        }

    }

    private fun performLogin() {
        //obtain input from user
        val email: EditText = findViewById(R.id.editEmailAddress1)
        val password: EditText = findViewById(R.id.editPassword1)

        //null checks on iinputs
        if (email.text.isEmpty() || password.text.isEmpty()){
            Toast.makeText(this, "Please fill in the fields", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val emailInput = email.text.toString()
        val passwordInput = password.text.toString()

        auth.signInWithEmailAndPassword(emailInput, passwordInput)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to main activity

                    val user = auth.currentUser
                    updateUI()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener{
                Toast.makeText(baseContext, "Authentication failed, ${it.localizedMessage}",
                    Toast.LENGTH_SHORT).show()
            }

    }

    private fun updateUI() {
        val intent = Intent(this, LocationsActivity::class.java)
        startActivity(intent)
        Toast.makeText(
            baseContext, "Success.",
            Toast.LENGTH_SHORT).show()
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        if(currentUser != null){
            updateUI()
        }
    }
}