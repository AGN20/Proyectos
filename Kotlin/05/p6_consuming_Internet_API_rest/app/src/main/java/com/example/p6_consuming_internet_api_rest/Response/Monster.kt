package com.example.p6_consuming_internet_api_rest.Response

import org.json.JSONArray

// Creamos el objeto Monster (Nuestros monstruos de Hirule)
data class Monster (
    var common_location : String,
    var description: String,
    var drops: String,
    var image: String,
    var name: String
    )