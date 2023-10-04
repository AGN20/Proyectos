package com.adgonu.shoppinglist.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.adgonu.shoppinglist.database.entities.ProductoEntity

@Database(entities = arrayOf(ProductoEntity::class), version = 1)
abstract class ProductoDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao

    companion object{
        private var instance: ProductoDao? = null

        fun getInstance(context: Application): ProductoDao{
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context,
                    ProductoDatabase::class.java,
                    "producto-db"
                ).build().productoDao().also { instance = it }
            }
        }
    }
}