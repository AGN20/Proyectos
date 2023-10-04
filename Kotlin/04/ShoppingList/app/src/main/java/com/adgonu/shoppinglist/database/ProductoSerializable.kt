package com.adgonu.shoppinglist.database

import androidx.lifecycle.MutableLiveData
import com.adgonu.shoppinglist.database.entities.ProductoEntity
import java.io.Serializable

data class ProductoSerializable(
    var list: MutableLiveData<MutableList<ProductoEntity>>
): Serializable
