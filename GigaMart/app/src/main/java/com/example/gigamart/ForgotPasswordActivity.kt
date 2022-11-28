package com.example.gigamart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth

class ForgotPasswordActivity : AppCompatActivity() {

    private lateinit var etPassword: EditText
    private lateinit var btnResetPassword: Button

    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        auth = FirebaseAuth.getInstance()

        etPassword=findViewById(R.id.editEmailAddressForgotPassword)
        btnResetPassword=findViewById(R.id.btnResetPassword)


        btnResetPassword.setOnClickListener{
            val ePassword = etPassword.text.toString()
            auth.sendPasswordResetEmail(ePassword)
                .addOnSuccessListener {
                    Toast.makeText(this, "Please check your email to proceed", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener{
                    Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT)

                }

        }


    }
}