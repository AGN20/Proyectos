package com.adgonu.artnutria.data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.Interface.OnItemClickListenerObra
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.viewmodel.ObraViewHolder

class ObraAdapter(private var obraList: List<Obra>, private val listener: OnItemClickListenerObra): RecyclerView.Adapter<ObraViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObraViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ObraViewHolder(layoutInflater.inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ObraViewHolder, position: Int) {
        val item = obraList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = obraList.size

    fun updateData(dataList: List<Obra>) {
        this.obraList = dataList
        notifyDataSetChanged()
    }

}