package com.adgonu.artnutria.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.Adapter.ObraAdapter
import com.adgonu.artnutria.data.Interface.OnItemClickListenerObra
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.data.modelo.Usuario
import com.adgonu.artnutria.viewmodel.FirebaseViewModel
import kotlinx.coroutines.launch

class HomeFragment : Fragment(), OnItemClickListenerObra {

    private lateinit var viewModel: FirebaseViewModel

    lateinit var rvObra: RecyclerView
    lateinit var obraList: ArrayList<Obra>
    lateinit var adapter: ObraAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar = view.findViewById(R.id.progressBarHome)
        progressBar.visibility = View.VISIBLE

        viewModel = FirebaseViewModel()

        obraList = arrayListOf<Obra>()

        rvObra = view.findViewById(R.id.rvObra)
        rvObra.setHasFixedSize(true)
        rvObra.visibility = View.VISIBLE
        rvObra.layoutManager = GridLayoutManager(requireContext(), 3)

        lifecycleScope.launch{
            obraList = viewModel.getAllObras(view.context) as ArrayList<Obra>
            adapter = ObraAdapter(obraList.sortedByDescending { it.fecha }, this@HomeFragment)

            rvObra.adapter = adapter
            progressBar.visibility = View.GONE
        }

        setup(view)
        createUser()

    }

    private fun setup(view: View) {

        var bttnObra: Button = view.findViewById(R.id.bttnObra)
        var bttnEtiqueta: Button = view.findViewById(R.id.bttnEtiqueta)
        var bttnUsuario: Button = view.findViewById(R.id.bttnUsuario)

        var svObra: SearchView = view.findViewById(R.id.seObra)
        var svEtiqueta: SearchView = view.findViewById(R.id.seEtiqueta)
        var svUsuario: SearchView = view.findViewById(R.id.seUsuario)

        // Dependiendo del boton que seleccionemos pondremos un buscador o otro y desabilitaremos el boton seleccionado
        bttnObra.setOnClickListener {
            svObra.visibility = View.VISIBLE
            svEtiqueta.visibility = View.GONE
            svUsuario.visibility = View.GONE
            bttnObra.isEnabled = false
            bttnEtiqueta.isEnabled = true
            bttnUsuario.isEnabled = true
        }

        bttnEtiqueta.setOnClickListener {
            svEtiqueta.visibility = View.VISIBLE
            svObra.visibility = View.GONE
            svUsuario.visibility = View.GONE
            bttnEtiqueta.isEnabled = false
            bttnObra.isEnabled = true
            bttnUsuario.isEnabled = true
        }

        bttnUsuario.setOnClickListener {
            svUsuario.visibility = View.VISIBLE
            svObra.visibility = View.GONE
            svEtiqueta.visibility = View.GONE
            bttnUsuario.isEnabled = false
            bttnObra.isEnabled = true
            bttnEtiqueta.isEnabled = true
        }

        // Busqueda por Obra
        svObra.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()){
                    adapter.updateData(obraList)
                }else{
                    filtrarNombre(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()){
                    adapter.updateData(obraList)
                }else{
                    filtrarNombre(newText)
                }
                return false
            }

        })

        // Busqueda por Etiquetas
        svEtiqueta.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()){
                    adapter.updateData(obraList)
                }else{
                    filtrarEtiqueta(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()){
                    adapter.updateData(obraList)
                }else{
                    filtrarEtiqueta(newText)
                }
                return false
            }

        })

        // Busqueda por usuario
        svUsuario.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String): Boolean {
                if (query.isEmpty()){
                    adapter.updateData(obraList)
                }else{
                    lifecycleScope.launch{
                        var user = viewModel.getIdUserName(query, view.context) ?: null
                        if(user != null){
                            filtrarUsuario(user.id)
                        }
                    }
                }

                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()){
                    adapter.updateData(obraList)
                }else{
                    lifecycleScope.launch{
                        var user = viewModel.getIdUserName(newText, view.context) ?: null
                        if(user != null){
                            filtrarUsuario(user.id)
                        }
                    }
                }
                return false
            }

        })

    }

    // Filtramos la busqueda por nombre, si la obra de la lista tiene un nombre parecido la añadira a la lista y actualizara la lista del RecyclerView
    private fun filtrarNombre(query: String) {

        var listaObrasFiltrada = mutableListOf<Obra>()

        for(obraData in obraList){
            if(obraData.nombre.toLowerCase().contains(query.toLowerCase())){
                listaObrasFiltrada.add(obraData)
            }
        }

        adapter.updateData(listaObrasFiltrada)

    }

    // Filtramos la busqueda por Etiquetas, si la obra de la lista, tiene etiquestas que se parezcan a lo que se a escrito, la añadira a la lista y actualizara la lista del RecyclerView
    private fun filtrarEtiqueta(query: String) {

        var listaObrasFiltrada = mutableListOf<Obra>()

        for(obraData in obraList){
            for(etiqueta in obraData.etiquetas)
                if(etiqueta.contains(query.toLowerCase())){
                    listaObrasFiltrada.add(obraData)
                }
        }

        adapter.updateData(listaObrasFiltrada)

    }

    // Filtramos la busqueda por Usuario, si la obra de la lista, tiene un usuario que se parezca a lo que se a escrito, la añadira a la lista y actualizara la lista del RecyclerView
    private fun filtrarUsuario(query: String) {

        var listaObrasFiltrada = mutableListOf<Obra>()

        for(obraData in obraList){
            if(obraData.uid.contains(query)){
                listaObrasFiltrada.add(obraData)
            }
        }

        adapter.updateData(listaObrasFiltrada)

    }

    //Si el usuario no existe, lo creamos
    fun createUser(){
        viewModel = FirebaseViewModel()

        lifecycleScope.launch {
            var user = viewModel.getUser(viewModel.getIdUser(), requireContext())

            //Comprobamos que el usuario exista
            if (user == null) {
                var usuario = Usuario(
                    "",
                    "",
                    viewModel.getMyUserEmail(),
                    "",
                    "",
                    "",
                    ArrayList(),
                    ArrayList()
                )
                viewModel.addUser(
                    usuario.descripcion,
                    usuario.email,
                    usuario.imagen,
                    usuario.nombre,
                    usuario.idioma,
                    usuario.favoritos,
                    usuario.obrasLikkes,
                    requireContext()
                )
                view?.findNavController()?.navigate(R.id.myUserFragment)
            }
        }
    }

    //Asignamos la funcion que tendra el clicar en una imagen
    override fun onItemClick(obra: Obra) {
        val bundle = Bundle()
        bundle.putSerializable(context?.getString(R.string.obra), obra)

        view?.findNavController()?.navigate(R.id.carouselFragment, bundle)

    }
}