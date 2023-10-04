package com.adgonu.artnutria.data.Adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.viewmodel.ObraImageViewHolder

class ObraImageAdapter(private var imagenList: List<Uri>): RecyclerView.Adapter<ObraImageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObraImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ObraImageViewHolder(layoutInflater.inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ObraImageViewHolder, position: Int) {
        val item = imagenList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = imagenList.size

    fun updateData(dataList: List<Uri>) {
        this.imagenList = dataList
        notifyDataSetChanged()
    }

}