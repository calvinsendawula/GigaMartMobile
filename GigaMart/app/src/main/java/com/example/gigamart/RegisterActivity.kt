package com.example.gigamart

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var sEmail:String
    private lateinit var sPassword:String
    private lateinit var tvEmail: TextView
    private lateinit var tvPassword: TextView
    private lateinit var tvConfirmPassword: TextView
    private lateinit var btnRegister: Button

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize Firebase Auth
        auth = Firebase.auth
        database = Firebase.database.reference
        //Variables
        tvEmail = findViewById<EditText>(R.id.editEmailAddress2)
        tvPassword = findViewById<EditText>(R.id.editPassword2)
        tvConfirmPassword = findViewById<EditText>(R.id.editConfirmPassword1)
        btnRegister = findViewById(R.id.btnRegister2)

        btnRegister.setOnClickListener{
            if (tvEmail.text.isEmpty() || tvPassword.text.isEmpty() || tvConfirmPassword.text.isEmpty()){
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val inputEmail = tvEmail.text.toString().trim()
            val inputPassword = tvPassword.text.toString().trim()
            val inputConfirmPassword = tvConfirmPassword.text.toString().trim()

            if(inputPassword == inputConfirmPassword){
                auth.createUserWithEmailAndPassword(inputEmail, inputPassword)
                    .addOnCompleteListener(this) { task ->
                        if (task.isSuccessful) {
                            // Sign in success,more to the next activity ie main activity
                            saveData()
                            updateUI()
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
            } else{
                Toast.makeText(this, "Password and Confirm password do not match", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
        }

        val signInClick = findViewById<TextView>(R.id.linkSignIn)
        signInClick.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    private fun saveData() {
        sEmail=tvEmail.text.toString().trim()
        sPassword=tvPassword.text.toString().trim()
        val user = UserModel(sEmail, sPassword)
        val userID = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("User").child(userID).setValue(user)
    }

    private fun updateUI() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)

        Toast.makeText(
            baseContext, "Account creation successful.",
            Toast.LENGTH_SHORT).show()
    }
}