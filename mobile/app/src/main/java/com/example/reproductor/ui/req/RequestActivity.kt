package com.example.reproductor.ui.req

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.reproductor.R
import com.example.reproductor.comunication.Comunicator
import com.example.reproductor.comunication.ComunicatorListener
import java.io.BufferedWriter
import java.io.OutputStreamWriter

class RequestActivity : AppCompatActivity(), ComunicatorListener {
    val entrada: String = "<solicitud>\n" +
            "\t<tipo>Pista</tipo>\n" +
            "\t<nombre>\"nombre_lista\"</nombre>\n" +
            "</solicitud>"

    private lateinit var btnSend: Button
    private lateinit var btnClear: Button
    private lateinit var btnStart: Button
    private lateinit var editText: EditText
    private lateinit var comunicator: Comunicator
    val serverHost = "172.21.192.1"
    val serverPort = 12345

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)
        val intent = Intent(this, CompleActivity::class.java)


        btnSend = findViewById(R.id.btnSend)
        btnClear = findViewById(R.id.btnClear)
        editText = findViewById(R.id.editText)
        editText.text = Editable.Factory.getInstance().newEditable(entrada)


        comunicator = Comunicator(editText)
        comunicator.setListener(this)

        btnClear.setOnClickListener {
            // Limpiar el contenido del EditText
            editText.text.clear()
        }

        btnSend.setOnClickListener {
            // Obtener el texto del EditText
            val text = editText.text.toString()
            comunicator.connectToServer(serverHost, serverPort)
            comunicator.sendDataToServer(text)

//            intent.putExtra("entrada", comunicator.getTextTemp())
//            startActivity(intent)
        }
    }

    // Función para mostrar un Toast con el texto
    private fun showToast(text: String) {
        println(text)
        // Aquí puedes mostrar el texto en un Toast o realizar otras acciones
        // Por ejemplo:
        // Toast.makeText(this, "Texto: $text", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        println("Destroy socket connection")
        comunicator.closeConnection()
    }

    override fun onServerResponse(response: String) {
        // Aquí recibes la respuesta del servidor y puedes realizar acciones con ella
        runOnUiThread {
            // Actualiza tu EditText en el hilo principal
//            editText.text = Editable.Factory.getInstance().newEditable(response)

            // Lanza la actividad CompleActivity después de recibir la respuesta
            val intent = Intent(this@RequestActivity, CompleActivity::class.java)
            intent.putExtra("entrada", response)
            startActivity(intent)

        }
    }
}