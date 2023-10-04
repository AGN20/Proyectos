package com.adgonu.shoppinglist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.RoomDatabase
import com.adgonu.shoppinglist.database.ProductoDao
import com.adgonu.shoppinglist.database.ProductoDatabase
import com.adgonu.shoppinglist.database.ProductoSerializable
import com.adgonu.shoppinglist.database.entities.ProductoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File
import java.nio.file.Files
import java.nio.file.StandardCopyOption

class ProductoViewModel(application: Application): AndroidViewModel(application) {
    val context = application

    var myDao: ProductoDao = ProductoDatabase.getInstance(context)

    val productListLD: MutableLiveData<MutableList<ProductoEntity>> = MutableLiveData()
    val updateProductLD: MutableLiveData<ProductoEntity?> = MutableLiveData()
    val deleteProductLD: MutableLiveData<Int> = MutableLiveData()
    val insertProductLD: MutableLiveData<ProductoEntity> = MutableLiveData()
    val productBiIdLD: ProductoEntity = ProductoEntity()


    fun getAllProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            productListLD.postValue(myDao.getAllProducts())
        }
    }

    fun getProductById(id:Int?){
        viewModelScope.launch(Dispatchers.IO) {
            val product = myDao.getProductById(id!!.toLong())
            productBiIdLD.id = product.id
            productBiIdLD.name = product.name
            productBiIdLD.quantity = product.quantity
            productBiIdLD.price = product.price
            productBiIdLD.total = product.total
        }
    }

    fun add(name:String, quantity:Int, price:Double, total:Double){
        viewModelScope.launch(Dispatchers.IO) {
            val id = myDao.addProduct(ProductoEntity(name = name, quantity = quantity, price = price, total = total))
            val recoveryProduct = myDao.getProductById(id)
            insertProductLD.postValue(recoveryProduct)
        }
    }

    fun delete(product: ProductoEntity){
        viewModelScope.launch(Dispatchers.IO) {
            val res = myDao.deleteProduct(product)
            if(res>0){
                deleteProductLD.postValue(product.id)
            }else{
                deleteProductLD.postValue(-1)
            }
        }
    }

    fun update(product: ProductoEntity){
        viewModelScope.launch(Dispatchers.IO) {
            val res = myDao.updateProduct(product)
            if(res>0){
                updateProductLD.postValue(product)
            }else{
                updateProductLD.postValue(null)
            }
        }
    }

    fun deleteAll(){

        viewModelScope.launch(Dispatchers.IO) {
            myDao.deleteAll()
        }

    }

}