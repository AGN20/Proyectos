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
import com.adgonu.shoppinglist.databinding.FragmentAddBinding
import com.adgonu.shoppinglist.viewmodel.ProductoViewModel

class AddFragment : Fragment() {

    lateinit var binding:FragmentAddBinding

    var navController: NavController? = null

    private var productoViewModel: ProductoViewModel = ProductoViewModel(application = Application())

    //Datos que tenemos que coger
    var nombre: String = ""
    var cantidad: Int = 0
    var precio: Double = 0.0
    var total: Double = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        //Boton que crea un nuevo producto en la lista y lleva a la pagina home
        binding.btSet.setOnClickListener{

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

            //Añadimos nuestro producto a la lista de Productos
            addProduct(nombre, cantidad, precio, total)

            //Pasamos al fragmento home
            navController?.navigate(R.id.action_addFragment_to_homeFragment)

        }

    }

    private fun addProduct(name: String, quantiy: Int, price: Double, total: Double) {
        productoViewModel.add(name, quantiy, price, total)
        clearFocus()
    }

    private fun clearFocus(){
        binding.etName.setText("")
        binding.etPrice.setText("")
        binding.etQuantity.setText("")
    }

}