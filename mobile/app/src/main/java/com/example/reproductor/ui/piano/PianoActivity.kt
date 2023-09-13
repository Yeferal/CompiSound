package com.example.reproductor.ui.piano

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Button
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.reproductor.R
import com.example.reproductor.comunication.Comunicator
import com.example.reproductor.comunication.ComunicatorListener
import com.example.reproductor.gramm.comunication.obj.DataPista
import com.example.reproductor.gramm.notas.MusicalNotes
import com.example.reproductor.ui.req.CompleActivity
import java.lang.StringBuilder

class PianoActivity : AppCompatActivity(), ComunicatorListener {
    private lateinit var btnPress: Button
    private lateinit var btnDo: Button
    private lateinit var btnDoS: Button
    private lateinit var btnRe: Button
    private lateinit var btnReS: Button
    private lateinit var btnMi: Button
    private lateinit var btnFa: Button
    private lateinit var btnFaS: Button
    private lateinit var btnSol: Button
    private lateinit var btnSolS: Button
    private lateinit var btnLa: Button
    private lateinit var btnLaS: Button
    private lateinit var btnSi: Button
    private lateinit var editTextCode: EditText
    private lateinit var btnClear: Button
    private lateinit var btnCompile: Button
    private lateinit var btnRec: Button
    private lateinit var tvRec: TextView
    private val handler = Handler()
    private var startTime = 0L
    private var isPressing = false
    private var isRecoding = false
    private val listaDatos = mutableListOf<DataPista>()
    private lateinit var comunicator: Comunicator
    val serverHost = "172.21.192.1"
    val serverPort = 12345

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_piano)

        btnDo = findViewById(R.id.btnDo)
        btnDoS = findViewById(R.id.btnDoS)
        btnRe = findViewById(R.id.btnRe)
        btnReS = findViewById(R.id.btnReS)
        btnMi = findViewById(R.id.btnMi)
        btnFa = findViewById(R.id.btnFa)
        btnFaS = findViewById(R.id.btnFaS)
        btnSol = findViewById(R.id.btnSol)
        btnSolS = findViewById(R.id.btnSolS)
        btnLa = findViewById(R.id.btnLa)
        btnLaS = findViewById(R.id.btnLaS)
        btnSi = findViewById(R.id.btnSi)
        btnClear = findViewById(R.id.btnClear)
        btnCompile = findViewById(R.id.btnCompile)
        btnRec = findViewById(R.id.btnRec)
        tvRec = findViewById(R.id.tvRec)

        editTextCode = findViewById(R.id.editTextCode)
        addActions()

        comunicator = Comunicator(editTextCode)
        comunicator.setListener(this)

        btnClear.setOnClickListener {
            listaDatos.clear()
            editTextCode.text = Editable.Factory.getInstance().newEditable("")
            paint()
        }

        btnCompile.setOnClickListener{
            // Obtener el texto del EditText
            if (isRecoding){
                val text = editTextCode.text.toString()
                comunicator.connectToServer(serverHost, serverPort)
                comunicator.sendDataToServer(text)
            }

        }

        btnRec.setOnClickListener{
            if (isRecoding){
                isRecoding = false
                tvRec.text = "Sin Grabar"
                tvRec.setTextColor(ContextCompat.getColor(this, R.color.black))
                listaDatos.clear()
                editTextCode.text = Editable.Factory.getInstance().newEditable("")
                paint()
            }else {
                isRecoding = true
                tvRec.text = "Grabando"
                tvRec.setTextColor(ContextCompat.getColor(this, R.color.red))
                listaDatos.clear()
                editTextCode.text = Editable.Factory.getInstance().newEditable("")
                paint()
            }

        }

        // Agrega un TextWatcher al EditText
        editTextCode.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                // Desplaza automáticamente hacia la última línea
                editTextCode.setSelection(s?.length ?: 0)
            }
        })

    }

    fun agregarDatoPistaALista(
        lista: MutableList<DataPista>,
        channel: Int,
        note: MusicalNotes,
        octave: Int,
        duracion: Int
    ) {
        if (isRecoding){
            val dataPista = DataPista(channel, note, octave, duracion)
            lista.add(dataPista)
        }
    }

    fun paint(){
        var txt: StringBuilder = StringBuilder();

        if (isRecoding){
            txt.append("<solicitud>\n")
            txt.append("\t<tipo>nombrePista</tipo>\n")
            for (item: DataPista in listaDatos){
                txt.append("\t<datos>\n")
                txt.append("\t\t<canal>").append(item.channel).append("</canal>\n")
                txt.append("\t\t<nota>").append(item.valNoteString).append("</nota>\n")
                txt.append("\t\t<octava>").append(item.octave).append("</octava>\n")
                txt.append("\t\t<duracion>").append(item.duracion).append("</duracion>\n")
                txt.append("\t</datos>\n")
            }
            txt.append("</solicitud>\n")
            editTextCode.text = Editable.Factory.getInstance().newEditable(txt.toString())
//            val scrollAmount = editTextCode.layout.getLineTop(editTextCode.lineCount) - editTextCode.height
//            if (scrollAmount > 0) {
//                editTextCode.scrollTo(0, scrollAmount)
//            } else {
//                editTextCode.scrollTo(0, 0)
//            }

        }


    }

    fun addActions() {
        btnDo.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Do: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.C, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnDoS.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Do#: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.C_S, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnRe.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Re: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.D, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnReS.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Re#: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.D_S, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnMi.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Mi: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.E, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnFa.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Fa: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.F, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnFaS.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Fa#: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.F_S, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnSol.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Sol: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.G, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnSolS.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Sol#: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.G_S, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnLa.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado La: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.A, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnLaS.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado La#: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.A_S, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })

        btnSi.setOnTouchListener(View.OnTouchListener { view, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    // Se presiona el botón
                    startTime = System.currentTimeMillis()
                    isPressing = true
                    handler.postDelayed(updateTimeRunnable, 100) // Actualizar cada 100 ms
                }
                MotionEvent.ACTION_UP -> {
                    // Se libera el botón
                    if (isPressing) {
                        handler.removeCallbacks(updateTimeRunnable)
                        val endTime = System.currentTimeMillis()
                        val totalTime = endTime - startTime
//                        editTextCode.text = "Tiempo presionado Si: $totalTime ms"
                        agregarDatoPistaALista(listaDatos, 1, MusicalNotes.B, 1, totalTime.toInt())
                        paint()
                    }
                    isPressing = false
                }
            }
            false
        })
    }

    private val updateTimeRunnable = object : Runnable {
        override fun run() {
            if (isPressing) {
                val currentTime = System.currentTimeMillis()
                val elapsedTime = currentTime - startTime
//                editTextCode.text = Editable.Factory.getInstance().newEditable("Tiempo presionado: $elapsedTime ms")
                handler.postDelayed(this, 100) // Actualizar cada 100 ms
            }
        }
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
            editTextCode.text = Editable.Factory.getInstance().newEditable(response)

            // Lanza la actividad CompleActivity después de recibir la respuesta

        }
    }
}