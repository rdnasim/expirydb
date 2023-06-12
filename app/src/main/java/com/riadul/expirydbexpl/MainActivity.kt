package com.riadul.expirydbexpl

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.riadul.expirydb.ExpiryDB

class MainActivity : AppCompatActivity() {

    private lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        context = applicationContext

        ExpiryDB.init(context)

        val contacts = mutableListOf(
            Person("John Doe", "1234567890"),
            Person("Jane Smith", "9876543210")
        )

        findViewById<View>(R.id.btnAddContact).setOnClickListener {
            ExpiryDB.book().write("contacts", contacts, 60000)
        }

        findViewById<View>(R.id.btnViewContact).setOnClickListener {
            ExpiryDB.book().read("contacts")
        }

        findViewById<View>(R.id.btnDeleteContact).setOnClickListener {
            ExpiryDB.book().delete("contacts")
        }

        findViewById<View>(R.id.btnDestroy).setOnClickListener {
            ExpiryDB.book().destroy()
        }

        findViewById<View>(R.id.btnGetAllKeys).setOnClickListener {
            ExpiryDB.book().getAllKeys()
        }
    }
}