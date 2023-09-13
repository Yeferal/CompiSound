package com.example.reproductor.ui.req

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.reproductor.R
import com.example.reproductor.comunication.Comunicator
import com.example.reproductor.gramm.comunication.LexicalAnalyzerCom
import com.example.reproductor.gramm.comunication.SyntaxAnalyzerCom
import com.example.reproductor.gramm.comunication.obj.RequesLista
import com.example.reproductor.gramm.comunication.obj.RequesPista
import java.io.IOException
import java.io.StringReader

class CompleActivity : AppCompatActivity() {

    val serverHost = "172.21.192.1"
    val serverPort = 12345

    private lateinit var btnSend: Button
    private lateinit var btnClear: Button
    private lateinit var btnStart: Button
    private lateinit var editText: EditText
    private lateinit var comunicator: Comunicator
    private var ent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comple)

        btnSend = findViewById(R.id.btnSend)
        btnClear = findViewById(R.id.btnClear)
        editText = findViewById(R.id.editText)
        val response = intent.getStringExtra("entrada")
        editText.text = Editable.Factory.getInstance().newEditable(response)

        btnClear.setOnClickListener {
            // Limpiar el contenido del EditText
            editText.text.clear()
        }

        btnSend.setOnClickListener {
            // Obtener el texto del EditText
            val text = editText.text.toString()// Crear un StringReader a partir de la cadena
            val stringReader = StringReader(text)

            try {
                var lexical: LexicalAnalyzerCom = LexicalAnalyzerCom(stringReader)
                var syntax: SyntaxAnalyzerCom = SyntaxAnalyzerCom(lexical)
                syntax.parse()
                if (lexical.lexicalErrors.size>0 || syntax.syntaxErrors.size>0){
                    for (lex in lexical.lexicalErrors){
                        println(lex.stringError)
                    }

                    for (syn in syntax.syntaxErrors){
                        println(syn.stringError)
                    }
                }else {
                    var obj = syntax.res
                    showToast(obj.toString())
                    if (obj is RequesLista) {
                        var req: RequesLista = obj;
                        val intent = Intent(this, ListasActivity::class.java)
                        intent.putExtra("entrada", req)
                        startActivity(intent)
                    } else if (obj is RequesPista) {
                        var req: RequesPista = obj;
                        val intent = Intent(this, PistasActivity::class.java)
                        intent.putExtra("entrada", req)
                        startActivity(intent)
                    }else{
                        println(obj.toString())
                    }

                }

//                showToast(obj.toString())
            }catch (e: IOException){
                e.printStackTrace()
            }

        }

    }

    // Función para mostrar un Toast con el texto
    private fun showToast(text: String) {
        println(text)
        // Aquí puedes mostrar el texto en un Toast o realizar otras acciones
        // Por ejemplo:
        // Toast.makeText(this, "Texto: $text", Toast.LENGTH_SHORT).show()
    }
}