package com.example.p6_consuming_internet_api_rest.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.p6_consuming_internet_api_rest.R
import com.example.p6_consuming_internet_api_rest.Response.Monster
import com.example.p6_consuming_internet_api_rest.ViewHolder.MonsterViewHolder

//Adaptador de nuestra aplicacion, le pasaremos el contexto y una lista de objetos Monster
class MonsterAdapter(val context: Context, val monsterList: List<Monster>): RecyclerView.Adapter<MonsterViewHolder>() {

    //Funcion que nos mete el layaut item_monster en el main
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MonsterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MonsterViewHolder(layoutInflater.inflate(R.layout.item_monster, parent, false))
    }

    //Variable con el tamaño de la lista de monsters
    override fun getItemCount(): Int = monsterList.size

    //Funcion que indica el elemento monster de dentro del monsterList que se va a mostrar
    override fun onBindViewHolder(holder: MonsterViewHolder, position: Int){
        val item = monsterList[position]
        holder.bind(item)
    }

}