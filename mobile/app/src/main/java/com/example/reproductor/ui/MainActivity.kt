package com.example.reproductor.ui

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.example.reproductor.R
import com.example.reproductor.comunication.Comunicator
import com.example.reproductor.ui.piano.PianoActivity
import com.example.reproductor.ui.req.RequestActivity
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
//import android.media.midi.
//import javax.s

class MainActivity : AppCompatActivity() {
    private lateinit var editText: TextView

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Al arrancar la pantalla
        editText = findViewById(R.id.textView)

        val btnPiano = findViewById<AppCompatButton>(R.id.btnPiano)
        val btnSolicitud = findViewById<AppCompatButton>(R.id.btnSolicitud)

        btnSolicitud.setOnClickListener {
            val intent = Intent(this, RequestActivity::class.java)
            startActivity(intent)
        }

        btnPiano.setOnClickListener {
            val intent = Intent(this, PianoActivity::class.java)
            startActivity(intent)
        }

        // Execute the network operation on a background thread

    }

}