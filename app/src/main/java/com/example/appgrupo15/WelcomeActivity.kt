package com.example.appgrupo15

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class WelcomeActivity : AppCompatActivity(), OnClickListener {
    private lateinit var txvUser: TextView
    private lateinit var fab: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
        txvUser = findViewById(R.id.txvUser)
        txvUser.setText(intent.getStringExtra("username"))

        fab = findViewById(R.id.fab)
        fab.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            fab -> {
                Snackbar.make(v, "Element added", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}