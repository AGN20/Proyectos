package com.adgonu.shoppinglist.adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.shoppinglist.R
import com.adgonu.shoppinglist.database.ProductoSerializable
import com.adgonu.shoppinglist.database.entities.ProductoEntity
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.text.DecimalFormat
import kotlin.math.roundToLong


class ProductoAdapter(
    var productoList: List<ProductoEntity>,
    val deleteProduct: (ProductoEntity) -> Unit,
) : RecyclerView.Adapter<ProductoAdapter.ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            return ViewHolder(layoutInflater.inflate(R.layout.item_producto, parent, false))

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int){
            val item = productoList[position]
            holder.itemView.tag = "ARG_PRODUCT"
            holder.bind(item, deleteProduct)
        }

        override fun getItemCount() = productoList.size

        fun getTotalPrice(productoList: List<ProductoEntity>): Double{
            var total = 0.0
            for(i in productoList){
                total += i.total
            }
            return total
        }

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

            val tvNombre = view.findViewById<TextView>(R.id.tvNameProduct)
            val tvCantidad = view.findViewById<TextView>(R.id.tvQuantityProduct)
            val tvPrecio = view.findViewById<TextView>(R.id.tvPriceProduct)
            val tvTotal = view.findViewById<TextView>(R.id.tvTotalProduct)
            val btDelete = view.findViewById<ImageView>(R.id.btDelete)



            fun bind(producto: ProductoEntity, deleteProduct: (ProductoEntity) -> Unit){
                tvNombre.text = producto.name
                tvCantidad.text = producto.quantity.toString()
                tvPrecio.text = String.format("%.2f€", producto.price)
                tvTotal.text = String.format("%.2f€", producto.total)

                //Eliminar el producto actual
                btDelete.setOnClickListener { deleteProduct(producto) }

                //Vamos al frame Update
                itemView.setOnClickListener {

                    val bundle: Bundle = Bundle()
                    bundle.putInt("ARG_PRODUCT", producto.id)


                    var navController: NavController? = null
                    navController = findNavController(it)
                    navController?.navigate(R.id.action_homeFragment_to_updateFragment, bundle)
                }

            }

        }
}