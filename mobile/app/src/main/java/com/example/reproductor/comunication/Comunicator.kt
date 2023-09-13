package com.example.reproductor.comunication

import android.widget.EditText
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.io.OutputStream
import java.net.Socket
import java.util.concurrent.Executors

interface ComunicatorListener {
    fun onServerResponse(response: String)
}

class Comunicator(editText: EditText){

    val serverHost = "172.21.192.1"
    val serverPort = 12345
    private var listener: ComunicatorListener? = null

    private lateinit var socket: Socket
    private lateinit var outputStream: ObjectOutputStream
    private lateinit var inputStream: ObjectInputStream
    private val executorService = Executors.newSingleThreadExecutor()
    private var textTemp: String = ""

    fun getTextTemp(): String {
        return textTemp
    }

    fun setListener(listener: ComunicatorListener) {
        this.listener = listener
    }

    fun connectToServer(host: String, port: Int) {
        executorService.execute {
            try {
                // Crea la conexión al servidor
                socket = Socket(host, port)

                // Inicializa los flujos de entrada y salida
                outputStream = ObjectOutputStream(socket.getOutputStream())
                inputStream = ObjectInputStream(socket.getInputStream())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun closeConnection() {
        executorService.execute {
            // Cierra la conexión al servidor
            try {
                inputStream.close()
                outputStream.close()
                socket.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun sendDataToServer(message: String) {
        executorService.execute {
            // Envía datos al servidor
            try {
                outputStream.writeObject(message)
                val response = inputStream.readObject() as String
                textTemp = response
                listener?.onServerResponse(response)
                closeConnection()
            }catch (e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun contarLineas(texto: String): Int {
        // Divide la cadena en líneas usando el carácter de nueva línea como separador y cuenta las líneas resultantes
        val lineas = texto.split("\n")
        return lineas.size
    }

}