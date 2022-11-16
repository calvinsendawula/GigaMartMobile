package com.example.gigamart

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuInflater
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView

class SecurityActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_security)
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
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}