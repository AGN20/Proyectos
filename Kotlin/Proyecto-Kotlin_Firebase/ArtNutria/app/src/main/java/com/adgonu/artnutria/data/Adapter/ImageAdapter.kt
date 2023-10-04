package com.adgonu.artnutria.data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.viewmodel.ImageViewHolder

class ImageAdapter(private var imagenList: List<String>): RecyclerView.Adapter<ImageViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(layoutInflater.inflate(R.layout.image_carusel_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val item = imagenList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = imagenList.size

    fun updateData(dataList: List<String>) {
        this.imagenList = dataList
        notifyDataSetChanged()
    }

}