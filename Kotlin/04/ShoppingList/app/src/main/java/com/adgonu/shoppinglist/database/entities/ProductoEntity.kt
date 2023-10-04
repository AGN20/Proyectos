package com.adgonu.shoppinglist.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "producto_entity")
data class ProductoEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var quantity: Int = 0,
    var price: Double = 0.00,
    var total: Double = 0.00
)