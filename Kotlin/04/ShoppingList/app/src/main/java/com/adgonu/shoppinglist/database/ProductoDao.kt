package com.adgonu.shoppinglist.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.RawQuery
import androidx.room.Update
import androidx.sqlite.db.SupportSQLiteQuery
import com.adgonu.shoppinglist.database.entities.ProductoEntity

@Dao
interface ProductoDao:MyDao {
    @Query("SELECT * FROM producto_entity")
    override fun getAllProducts(): MutableList<ProductoEntity>

    @Insert
    override fun addProduct(productoEntity: ProductoEntity): Long

    @Query("SELECT * FROM producto_entity WHERE id LIKE :id")
    override fun getProductById(id: Long): ProductoEntity

    @Update
    override fun updateProduct(productoEntity: ProductoEntity): Int

    @Delete
    override fun deleteProduct(productoEntity: ProductoEntity): Int

    @Query("DELETE FROM producto_entity")
    override fun deleteAll()

}