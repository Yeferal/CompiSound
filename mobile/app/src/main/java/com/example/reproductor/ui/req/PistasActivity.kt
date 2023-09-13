package com.example.reproductor.ui.req

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import com.example.reproductor.R
import com.example.reproductor.gramm.comunication.obj.DataChannel
import com.example.reproductor.gramm.comunication.obj.DataListaListas
import com.example.reproductor.gramm.comunication.obj.DataNote
import com.example.reproductor.gramm.comunication.obj.DataStructPista
import com.example.reproductor.gramm.comunication.obj.RequesLista
import com.example.reproductor.gramm.comunication.obj.RequesPista
import com.example.reproductor.gramm.comunication.obj.TrackOnList

class PistasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pistas)

        val lvPistas = findViewById<ListView>(R.id.lvPistas)
        val lvPista = findViewById<ListView>(R.id.lvPista)
        val textListaPistas = findViewById<TextView>(R.id.textListaPistas)

        // Recuperar el objeto MiObjeto del intent
        // Recuperar el objeto MiObjeto del intent
        val listaDeDatos = mutableListOf<TrackOnList>()
        val listaDeDatosPistas = mutableListOf<DataNote>()
        val intent = intent
        val objetoRecibido: RequesPista? = intent.getSerializableExtra("entrada") as RequesPista?

        if (objetoRecibido!=null){
            listaDeDatos.clear()
            for (elemento: TrackOnList in objetoRecibido.listPistas){
                listaDeDatos.add(elemento)
            }
            val adapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeDatos)
            lvPistas.adapter = adapter
        }

        lvPistas.setOnItemClickListener(){parent,view,position,id->
//            //SETEAR EL TITULO
//            //SETER LA LISTA
//
            if (objetoRecibido != null) {
                listaDeDatosPistas.clear()
                    for (elemnt: DataChannel in objetoRecibido.listDataStructPista.get(position).listChannels)
                        for (item: DataNote in elemnt.listNotes)
                    listaDeDatosPistas.add(item)
//                textListaPistas.text = "Lista: "+objetoRecibido.dataListaPistas.get(position).nameList+"\t\tAleatorio: "+objetoRecibido.dataListaPistas.get(position).isRandom
            }
            val adapter2: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeDatosPistas)
            lvPista.adapter = adapter2
        }
    }
}