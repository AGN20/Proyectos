package com.adgonu.shoppinglist.ui.fragmnets

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.shoppinglist.R
import com.adgonu.shoppinglist.adapters.ProductoAdapter
import com.adgonu.shoppinglist.database.ProductoDao
import com.adgonu.shoppinglist.database.ProductoDatabase
import com.adgonu.shoppinglist.database.entities.ProductoEntity
import com.adgonu.shoppinglist.databinding.FragmentHomeBinding
import com.adgonu.shoppinglist.viewmodel.ProductoViewModel

class HomeFragment : Fragment() {

    private lateinit var  binding: FragmentHomeBinding
    var navController: NavController? =null

    lateinit var recyclerView: RecyclerView
    var products: MutableList<ProductoEntity> = mutableListOf()

    private lateinit var productViewModel:ProductoViewModel

    lateinit var adapter: ProductoAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // RECICLER VIEW

        productViewModel = ViewModelProvider(this)[ProductoViewModel::class.java]

        productViewModel.getAllProducts()

        productViewModel.productListLD.observe(viewLifecycleOwner){
            products.clear()
            products.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
            binding.etNumTotalPrice.text =  String.format("%.2f€", adapter.getTotalPrice(products))
        }

        productViewModel.updateProductLD.observe(viewLifecycleOwner){ productUpdated ->
            if(productUpdated == null){
                showMessage("Error updating task")
            }
        }

        productViewModel.deleteProductLD.observe(viewLifecycleOwner){ id ->
            if(id != -1){
                val producto = products.filter {
                    it.id == id
                }[0]
                val pos = products.indexOf(producto)
                products.removeAt(pos)
                recyclerView.adapter?.notifyItemRemoved(pos)
            }else{
                showMessage("Error deleting task")
            }
        }

        productViewModel.insertProductLD.observe(viewLifecycleOwner){
            products.add(it)
            recyclerView.adapter?.notifyItemInserted(products.size)

        }

        setUpRecyclerView()



        navController = findNavController()

        //Boton que te lleva al AddFragment
        binding.btAdd.setOnClickListener {
            navController?.navigate(R.id.action_homeFragment_to_addFragment)
        }

    }

    // RECICLER VIEW

    private fun showMessage(s: String) {
        Toast.makeText(this.context, s, Toast.LENGTH_SHORT).show()
    }


    fun setUpRecyclerView() {
        adapter = ProductoAdapter(products, { productoEntity -> deleteProduct(productoEntity) })
        recyclerView = binding.rvProduct
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        recyclerView.adapter = adapter
    }

    private fun deleteProduct(productoEntity: ProductoEntity) {

        if(productoEntity.quantity > 1){
            --productoEntity.quantity
            productoEntity.total = productoEntity.quantity * productoEntity.price
            productViewModel.update(productoEntity)
            navController?.navigate(R.id.homeFragment)
        }else{
            productViewModel.delete(productoEntity)
        }
    }

}