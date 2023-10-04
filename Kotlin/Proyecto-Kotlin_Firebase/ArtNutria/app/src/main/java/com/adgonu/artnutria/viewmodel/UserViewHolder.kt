package com.adgonu.artnutria.viewmodel

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.data.modelo.Usuario
import com.adgonu.artnutria.databinding.UsuarioItemBinding
import com.squareup.picasso.Picasso

class UserViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = UsuarioItemBinding.bind(view)

    fun bind (usuario: Usuario){

        Picasso.get().load(usuario.imagen).into(binding.ivuserus)
        binding.tvnameus.text = usuario.nombre
        binding.tvemailus.text = usuario.email

    }
}