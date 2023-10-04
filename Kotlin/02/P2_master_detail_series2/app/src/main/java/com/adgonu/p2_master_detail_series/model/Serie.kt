package com.adgonu.p2_master_detail_series.model

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.adgonu.p2_master_detail_series.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.reflect.Type

data class Serie (
    var id:Int = 0,
    var name:String = "",
    var language:String = "",
    var genres:ArrayList<String> = ArrayList(),
    var status:String = "",
    var premiered:String = "",
    var officialSite:String = "",
    var rating:Float = 0.0f,
    var image:String = "",
    var summary:String = "",
){
    companion object{
        var serieList = mutableListOf<Serie>()

        //leer los datos del JSON, es necesario pasar el contexto
        fun loadSeries(context: Context):MutableList<Serie>?{

            val raw = context.resources.openRawResource(R.raw.datos_json)
            val rd = BufferedReader(InputStreamReader(raw))
            val listType: Type = object : TypeToken<MutableList<Serie?>?>() {}.type

            val gson = Gson()

            serieList = gson.fromJson(rd, listType)

            return serieList
        }

        fun getSerieBiId(id:Int): Serie?{
            val lista =  serieList.filter {
                it.id == id
            }
            return if(lista.isNullOrEmpty()) null else lista[0]
        }

        fun String.toBitmap(context: Context): Bitmap {
            val imageName = this.split(".")[0]
            val id = context.resources.getIdentifier(
                imageName,
                "drawable",
                context.packageName
            )

            val inputStream = context.resources.openRawResource(id)
            return BitmapFactory.decodeStream(inputStream)
        }

    }
}