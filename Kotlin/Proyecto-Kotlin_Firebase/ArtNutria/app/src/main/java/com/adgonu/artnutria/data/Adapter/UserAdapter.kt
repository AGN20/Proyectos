package com.adgonu.artnutria.data.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.Interface.OnItemClickListenerUsuario
import com.adgonu.artnutria.data.modelo.Usuario
import com.adgonu.artnutria.viewmodel.UserViewHolder

class UserAdapter(private var userList: List<Usuario>, private val listener: OnItemClickListenerUsuario): RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return UserViewHolder(layoutInflater.inflate(R.layout.usuario_item, parent, false))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = userList[position]
        holder.bind(item)

        holder.itemView.setOnClickListener {
            listener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int = userList.size

    fun updateData(dataList: List<Usuario>) {
        this.userList = dataList
        notifyDataSetChanged()
    }
}