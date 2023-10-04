package com.adgonu.shoppinglist.database

import androidx.sqlite.db.SupportSQLiteQuery
import com.adgonu.shoppinglist.database.entities.ProductoEntity

interface MyDao {
    fun getAllProducts(): MutableList<ProductoEntity>

    fun addProduct(productoEntity: ProductoEntity):Long

    fun getProductById(id: Long): ProductoEntity

    fun updateProduct(productoEntity: ProductoEntity):Int

    fun deleteProduct(productoEntity: ProductoEntity):Int

    fun deleteAll()

}