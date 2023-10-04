package com.adgonu.shoppinglist.ui.fragmnets


import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.adgonu.shoppinglist.R
import com.adgonu.shoppinglist.database.entities.ProductoEntity
import com.adgonu.shoppinglist.databinding.FragmentUpdateBinding
import com.adgonu.shoppinglist.viewmodel.ProductoViewModel


class UpdateFragment : Fragment(){

    lateinit var binding: FragmentUpdateBinding

    var navController: NavController? = null

    private var productoViewModel: ProductoViewModel = ProductoViewModel(Application())
    private var productoId: Int? = null

    var producto: ProductoEntity? = null

    //Datos que tenemos que coger
    var nombre: String = ""
    var cantidad: Int = 0
    var precio: Double = 0.0
    var total: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            productoId = it.getInt("ARG_PRODUCT", -1)
            productoViewModel.getProductById(productoId)
            producto = productoViewModel.productBiIdLD
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUpdateBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        binding.etName.setText(producto?.name)
        binding.etQuantity.setText(producto?.quantity.toString())
        binding.etPrice.setText(producto?.price.toString())


        //Boton para updatear los elementos y ir al home
        binding.btUpdate.setOnClickListener{


            //Cojemos los datos que necesitamos
            nombre = binding.etName.text.toString()

            if(binding.etQuantity.text.toString() == ""){
                cantidad = 1
            }else{
                var sCantidad = binding.etQuantity.text.toString()
                cantidad = sCantidad.toInt()
            }

            if(binding.etPrice.text.toString() == ""){
                precio = 0.0
            }else{
                var sPrecio = binding.etPrice.text.toString()
                precio = sPrecio.toDouble()
            }

            //Calculamos el total
            total = cantidad.toInt() * precio.toDouble()

            //Comprobacion de datos
            println( "LOG_" + " " + nombre + " " + cantidad + " " + precio + " " + total)

            //Añadimos los datos que necesita el producto
            producto!!.name = nombre
            producto!!.quantity = cantidad
            producto!!.price = precio
            producto!!.total = total

            //Añadimos nuestro producto a la lista de Productos
            updateProduct(producto!!)


            //Pasamos al fragmento home
            navController?.navigate(R.id.action_updateFragment_to_homeFragment)

        }

    }


    private fun updateProduct(productoEntity: ProductoEntity) {
        productoViewModel.update(productoEntity)
        clearFocus()
    }

    private fun clearFocus(){
        binding.etName.setText("")
        binding.etPrice.setText("")
        binding.etQuantity.setText("")
    }

}