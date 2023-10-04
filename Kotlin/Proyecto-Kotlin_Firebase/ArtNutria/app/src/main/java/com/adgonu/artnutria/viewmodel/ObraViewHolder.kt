package com.adgonu.artnutria.viewmodel


import android.view.View
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.databinding.ImageItemBinding
import com.squareup.picasso.Picasso

class ObraViewHolder(view: View): RecyclerView.ViewHolder(view) {
    private val binding = ImageItemBinding.bind(view)
    private var context = view.context

    fun bind (obra: Obra){

        var extension = obra.imagenes[0].substringAfterLast("_", "")
        extension = extension.substring( 0, 5)

        if(extension == context.getString(R.string.image)){
            binding.imageObra.visibility = View.VISIBLE
            binding.videoObra.visibility = View.GONE
            Picasso.get().load(obra.imagenes[0]).into(binding.imageObra)

        }
        else if(extension == context.getString(R.string.video)){
            val mediaControler = MediaController(itemView.context)
            binding.imageObra.visibility = View.GONE
            binding.videoObra.visibility = View.VISIBLE
            mediaControler.setAnchorView(binding.videoObra)
            binding.videoObra.setMediaController(mediaControler)
            binding.videoObra.setVideoPath(obra.imagenes[0])
        }

    }
}