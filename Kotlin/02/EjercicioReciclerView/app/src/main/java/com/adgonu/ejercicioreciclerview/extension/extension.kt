package com.adgonu.ejercicioreciclerview.extension

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import java.io.InputStream

//This function pass an string like "batman.jpg" that is in raw folder to an Bitmap
fun String.toBitmap(context:Context):Bitmap{
    val imageName = this.split(".")[0]
    val id = context.resources.getIdentifier(
        imageName,
        "raw",
        context.packageName
    )

    val inputStream =context.resources.openRawResource(id)
    return  BitmapFactory.decodeStream(inputStream)
}

fun ImageView.setBitmapfromString (nombre:String){
    val nombreImagen = nombre.split(".")[0]
    val id = context.resources.getIdentifier(
        nombreImagen,
        "raw",
        context.packageName
    )

    lateinit var inputStream: InputStream

    inputStream = context.resources.openRawResource(id)

    val imagen = BitmapFactory.decodeStream(inputStream)
    this.setImageBitmap(imagen)

}