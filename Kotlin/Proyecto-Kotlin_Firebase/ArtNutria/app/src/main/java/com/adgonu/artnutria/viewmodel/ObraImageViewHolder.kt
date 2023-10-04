package com.adgonu.artnutria.viewmodel

import android.net.Uri
import android.view.View
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.databinding.ImageItemBinding
import com.squareup.picasso.Picasso

class ObraImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ImageItemBinding.bind(view)
    private var context = view.context

    fun bind (image: Uri){
        var contextResolve = context.contentResolver
        var extension = contextResolve.getType(image)!!.split("/")

        if(extension[0] == context.getString(R.string.image)){
            binding.imageObra.visibility = View.VISIBLE
            binding.videoObra.visibility = View.GONE
            Picasso.get().load(image).into(binding.imageObra)

        }
        else if(extension[0] == context.getString(R.string.video)){
            val mediaControler = MediaController(itemView.context)
            binding.imageObra.visibility = View.GONE
            binding.videoObra.visibility = View.VISIBLE
            mediaControler.setAnchorView(binding.videoObra)
            binding.videoObra.setMediaController(mediaControler)
            binding.videoObra.setVideoURI(image)
        }

    }

}