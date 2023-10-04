package com.adgonu.artnutria.data.modelo

/** Data class de los usuarios **/
data class Usuario(
    var id: String = "",
    var descripcion: String = "",
    var email: String = "",
    var imagen: String = "",
    var nombre: String = "",
    var idioma: String = "",
    var favoritos: ArrayList<String> = ArrayList(),
    var obrasLikkes: ArrayList<String> = ArrayList()
): java.io.Serializable
