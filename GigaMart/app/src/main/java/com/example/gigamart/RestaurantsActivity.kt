package com.example.gigamart

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.View.inflate
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.resources.Compatibility.Api21Impl.inflate
import androidx.cardview.widget.CardView
import androidx.core.content.res.ColorStateListInflaterCompat.inflate
import androidx.core.content.res.ComplexColorCompat.inflate
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RestaurantsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurants)

        val restaurantClick1 = findViewById<CardView>(R.id.btnTheShack)
        restaurantClick1.setOnClickListener {
            val intent = Intent(this, TheShackActivity::class.java)
            startActivity(intent)
        }

        val restaurantClick2 = findViewById<CardView>(R.id.btnTonis)
        restaurantClick2.setOnClickListener {
            val intent = Intent(this, TheShackActivity::class.java)
            startActivity(intent)
        }

        val restaurantClick3 = findViewById<CardView>(R.id.btnMiams)
        restaurantClick3.setOnClickListener {
            val intent = Intent(this, TheShackActivity::class.java)
            startActivity(intent)
        }
    }

    fun showMenu(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.menuInflater
        inflater.inflate(R.menu.dashboard_menu, popup.menu)
        popup.show()
        // Registering popup with OnMenuItemClickListener
        popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.itemAccount -> {
                    dashAccount()
                    true
                }
                R.id.itemAddress -> {
                    dashAddress()
                    true
                }
                R.id.itemSecurity -> {
                    dashSecurity()
                    true
                }
                R.id.itemPaymentMethod -> {
                    dashPaymentMethod()
                    true
                }
                R.id.itemPastOrders -> {
                    dashPastOrders()
                    true
                }
                R.id.itemLogout -> {
                    dashLogout()
                    true
                }
                else -> false
            }
        })
    }

    private fun dashAccount() {
        val intent = Intent(this, AccountActivity::class.java)
        startActivity(intent)
    }

    private fun dashAddress() {
        val intent = Intent(this, AddressActivity::class.java)
        startActivity(intent)
    }

    private fun dashSecurity() {
        val intent = Intent(this, SecurityActivity::class.java)
        startActivity(intent)
    }

    private fun dashPaymentMethod() {
        val intent = Intent(this, PaymentMethodActivity::class.java)
        startActivity(intent)
    }

    private fun dashPastOrders() {
        val intent = Intent(this, PastOrdersActivity::class.java)
        startActivity(intent)
    }

    private fun dashLogout() {
        Firebase.auth.signOut()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}