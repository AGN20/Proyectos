package com.adgonu.artnutria.viewmodel

import android.view.View
import android.widget.MediaController
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.databinding.ImageCaruselItemBinding
import com.squareup.picasso.Picasso

class ImageViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ImageCaruselItemBinding.bind(view)
    private val context = view.context

    fun bind (image: String){

        var extension = image.substringAfterLast("_", "")
        extension = extension.substring( 0, 5)

        if(extension == context.getString(R.string.image)){
            binding.imageCarObra.visibility = View.VISIBLE
            binding.videoCarObra.visibility = View.GONE
            Picasso.get().load(image).into(binding.imageCarObra)

        }
        else if(extension == context.getString(R.string.video)){
            val mediaControler = MediaController(itemView.context)
            binding.imageCarObra.visibility = View.GONE
            binding.videoCarObra.visibility = View.VISIBLE
            mediaControler.setAnchorView(binding.videoCarObra)
            binding.videoCarObra.setMediaController(mediaControler)
            binding.videoCarObra.setVideoPath(image)
        }

    }
}