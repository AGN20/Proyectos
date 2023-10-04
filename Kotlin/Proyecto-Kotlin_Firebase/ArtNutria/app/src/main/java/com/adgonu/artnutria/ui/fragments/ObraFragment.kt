package com.adgonu.artnutria.ui.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.Adapter.ObraImageAdapter
import com.adgonu.artnutria.viewmodel.FirebaseViewModel

class ObraFragment : Fragment() {

    private lateinit var viewModel: FirebaseViewModel
    private val GALLERY_INTENT = 1
    var imagenes: ArrayList<Uri> = ArrayList()
    lateinit var rvObraImagen: RecyclerView
    lateinit var adapter: ObraImageAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_obra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = FirebaseViewModel()

        rvObraImagen = view.findViewById(R.id.rvObraImagen)
        rvObraImagen.setHasFixedSize(true)
        rvObraImagen.visibility = View.VISIBLE
        rvObraImagen.layoutManager = GridLayoutManager(requireContext(), 3)

        adapter = ObraImageAdapter(imagenes)
        rvObraImagen.adapter = adapter

        subirImagenObra(view)
        subirObra(view)
    }

    fun subirObra(view: View){
        var bttnSubirObra: Button = view.findViewById(R.id.bttnSubirObra)

        bttnSubirObra.setOnClickListener {
            var etiquetas = ArrayList<String>()

            val progressBar: ProgressBar = view.findViewById(R.id.progressBarObra)
            progressBar.visibility = View.VISIBLE

            val uid: String = viewModel.getIdUser()
            val nombre: EditText = requireView().findViewById<EditText?>(R.id.edtNombreObra)
            val etiqueta: EditText = requireView().findViewById(R.id.edtEtiquetasObra)
            val imagen = imagenes

            var etiquetasEnviar = ArrayList<String>()

            if(etiqueta.text.toString().contains(",")) {
                etiquetasEnviar = etiqueta.text.toString().lowercase().split(",") as ArrayList<String>
            }else{
                etiquetasEnviar.add(etiqueta.text.toString().lowercase())
            }

            viewModel.addObra(uid, nombre.text.toString(), imagen, etiquetasEnviar, requireContext())
            progressBar.visibility = View.GONE

        }
    }

    // Funcion que agrega las imagenes de la obra, abre la galeria y puedes añadir las imagenes
    fun subirImagenObra(view: View){
        val btnSelecImage: Button = view.findViewById(R.id.bttnSeleccionarImagen)
        btnSelecImage.setOnClickListener{

            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, GALLERY_INTENT)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GALLERY_INTENT && resultCode == Activity.RESULT_OK){
            var image_url = data?.data
            imagenes.add(image_url!!)
            adapter = ObraImageAdapter(imagenes)
            rvObraImagen.adapter = adapter
        }
    }

}