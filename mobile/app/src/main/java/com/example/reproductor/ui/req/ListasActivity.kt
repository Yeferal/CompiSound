package com.example.reproductor.ui.req

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.reproductor.R
import com.example.reproductor.gramm.comunication.obj.DataListaListas
import com.example.reproductor.gramm.comunication.obj.DataListaPistas
import com.example.reproductor.gramm.comunication.obj.RequesLista
import com.example.reproductor.gramm.comunication.obj.TrackOnList


class ListasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listas)

        val lvLista = findViewById<ListView>(R.id.lvLista)
        val lvListaPistas = findViewById<ListView>(R.id.lvListaPistas)
        val textListaPistas = findViewById<TextView>(R.id.textListaPistas)

        // Recuperar el objeto MiObjeto del intent
        // Recuperar el objeto MiObjeto del intent
        val listaDeDatos = mutableListOf<DataListaListas>()
        val listaDeDatosPistas = mutableListOf<TrackOnList>()
        val intent = intent
        val objetoRecibido: RequesLista? = intent.getSerializableExtra("entrada") as RequesLista?

        if (objetoRecibido!=null){
            listaDeDatos.clear()
            for (elemento: DataListaListas in objetoRecibido.dataListaListas){
                listaDeDatos.add(elemento)
            }
            val adapter: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeDatos)
            lvLista.adapter = adapter
        }

        lvLista.setOnItemClickListener(){parent,view,position,id->
            //SETEAR EL TITULO
            //SETER LA LISTA

            if (objetoRecibido != null) {
                listaDeDatosPistas.clear()
                for (elemento: TrackOnList in objetoRecibido.dataListaPistas.get(position).listPist){
                    listaDeDatosPistas.add(elemento)
                }
                textListaPistas.text = "Lista: "+objetoRecibido.dataListaPistas.get(position).nameList+"\t\tAleatorio: "+objetoRecibido.dataListaPistas.get(position).isRandom
            }
            val adapter2: ArrayAdapter<*> = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaDeDatosPistas)
            lvListaPistas.adapter = adapter2
        }


    }
}