package com.adgonu.artnutria.data.modelo

data class Obra(
    var uid: String = "",
    var id: String = "",
    var nombre: String = "",
    var imagenes: ArrayList<String> = ArrayList(),
    var etiquetas: ArrayList<String> = ArrayList(),
    var fecha: String = "",
    var likkes: Int = 0
): java.io.Serializable
