package com.example.p6_consuming_internet_api_rest.ViewHolder

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.p6_consuming_internet_api_rest.Response.Monster
import com.example.p6_consuming_internet_api_rest.databinding.ItemMonsterBinding

class MonsterViewHolder(view: View):RecyclerView.ViewHolder(view) {
    private val binding = ItemMonsterBinding.bind(view)
    private val view: View = view

    //Ponemos en variables los campos del item_monster
    var tvName: TextView = binding.tvName
    var ivImage: ImageView = binding.ivImage
    var tvLocation: TextView = binding.tvLocation
    var tvDescription: TextView = binding.tvDescription
    var tvDrops: TextView = binding.tvDrops

    fun bind (monster: Monster) {
        tvName.text = monster.name
        Glide.with(view).load(monster.image).fitCenter().override(316,181).into(ivImage)
        if(monster.common_location.isNullOrEmpty() || monster.common_location == "null"){
            tvLocation.text = "Location: undefined"
        }else{
            tvLocation.text = "Location: " + monster.common_location
        }
        tvDescription.text = monster.description
        if(monster.drops.isNullOrEmpty() || monster.drops == "null"){
            tvDrops.text = "Drops: none"
        }else{
            tvDrops.text = "Drops: " + monster.drops
        }
    }

}