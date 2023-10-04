package com.adgonu.artnutria.ui.fragments

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
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
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.launch

class MyUserFragment : Fragment(), OnItemClickListenerObra {

    private lateinit var viewModel: FirebaseViewModel
    private val GALLERY_INTENT = 1
    lateinit var obraList: ArrayList<Obra>
    lateinit var adapter: ObraAdapter
    lateinit var rvMyObra: RecyclerView

    var imagen_url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_user, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        subirImagenUsuario(view)
        subirObra(view)
        setup(view)
        followUser(view)
        guardarUsuario(view)
        followObra(view)

        obraList = arrayListOf<Obra>()

        rvMyObra = view.findViewById(R.id.rvMyObra)
        rvMyObra.setHasFixedSize(true)
        rvMyObra.visibility = View.VISIBLE
        rvMyObra.layoutManager = GridLayoutManager(requireContext(), 3)

        lifecycleScope.launch{
            obraList = viewModel.getObrasUser(viewModel.getIdUser(), view.context) as ArrayList<Obra>

            if(obraList.size != 0){
                adapter = ObraAdapter(obraList.sortedByDescending { it.fecha }, this@MyUserFragment)

                rvMyObra.adapter = adapter
            }

        }

        progressBar.visibility = View.GONE

    }

    // Funcion que te dirije a la lista de obras a las que as dado follow
    private fun followObra(view: View) {
        val btFollowObra: Button = view.findViewById(R.id.bttFollow)
        btFollowObra.setOnClickListener { view?.findNavController()?.navigate(R.id.followObraFragment) }
    }

    // Funcion que guarda los datos de los usuarios
    private fun guardarUsuario(view: View) {
        val btGuardar: Button = view.findViewById(R.id.bttCambios)
        btGuardar.setOnClickListener {
            lifecycleScope.launch{
                var usuario_nuevo = viewModel.getUser(viewModel.getIdUser(), view.context)
                crearUsuario(view, usuario_nuevo!!)
            }
        }
    }

    // Funcion que crea el usuario
    fun crearUsuario(view: View, user: Usuario){

        val nombre: EditText = view.findViewById(R.id.edNombre)
        val email: EditText = view.findViewById(R.id.edCorreo)
        val idioma: EditText = view.findViewById(R.id.edIdioma)
        val descripcion: EditText = view.findViewById(R.id.edDescripcion)

        viewModel.addUser(descripcion.text.toString(), email.text.toString(), user.imagen, nombre.text.toString(), idioma.text.toString(), ArrayList<String>(), ArrayList<String>(), requireContext())
        view?.findNavController()?.navigate(R.id.homeFragment)

    }

    // Funcion que modifica la imagen del usuario, abre la galeria y puedes cambiar la imagen
    fun subirImagenUsuario(view: View){
        val imagen: ImageView = view.findViewById(R.id.ivUser)
        imagen.setOnClickListener{
            var intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, GALLERY_INTENT)
        }
    }

    // Funcion que te dirije a crear las obras
    fun subirObra(view: View){
       val bttnSubir: Button = view.findViewById(R.id.bttnSubir)
        bttnSubir.setOnClickListener { view?.findNavController()?.navigate(R.id.obraFragment) }
    }

    // Funcion que te dirije a la lista de los usuarios seguidos
    fun followUser(view: View){
        val bttnSeguir: Button = view.findViewById(R.id.bttSiguiendo)
        bttnSeguir.setOnClickListener { view?.findNavController()?.navigate(R.id.followUserFragment) }
    }

    fun setup(view: View){
        viewModel = FirebaseViewModel()

        val email: EditText = view.findViewById(R.id.edCorreo)
        val btGuardar: Button = view.findViewById(R.id.bttCambios)
        val btSubir: Button = view.findViewById(R.id.bttnSubir)
        val btSiguiendo: Button = view.findViewById(R.id.bttSiguiendo)
        val btFollow: Button = view.findViewById(R.id.bttFollow)

        lifecycleScope.launch{
            var user = viewModel.getUser(viewModel.getIdUser(), view.context)!!

            val nombre: EditText = view.findViewById(R.id.edNombre)
            val email: EditText = view.findViewById(R.id.edCorreo)
            val idioma: EditText = view.findViewById(R.id.edIdioma)
            val descripcion: EditText = view.findViewById(R.id.edDescripcion)
            val imagen: CircleImageView = view.findViewById(R.id.ivUser)

            //Si el usuario es "notuser@gmail.com" no permitiremos cambiar nada
            if(user.email == context?.getString(R.string.NotUser)){
                nombre.isEnabled = false
                email.isEnabled = false
                idioma.isEnabled = false
                descripcion.isEnabled = false
                imagen.isEnabled = false
                btGuardar.isEnabled = false
                btSubir.isEnabled = false
                btSiguiendo.isEnabled = false
                btFollow.isEnabled = false
            }

            //Recogemos los datos del usuario
            nombre.setText(user.nombre)
            email.setText(user.email)
            idioma.setText(user.idioma)
            descripcion.setText(user.descripcion)
            if(user.imagen != ""){
                Picasso.get().load(user.imagen).into(imagen)
            }else{
                Picasso.get().load(R.drawable.imagen_usuario).into(imagen)
            }
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == GALLERY_INTENT && resultCode == RESULT_OK){
            var imagen_uri = data?.data!!
            imagen_url = imagen_uri.toString()
            viewModel.addImage(imagen_uri!!, viewModel.getIdUser(), requireContext())
        }
    }

    override fun onItemClick(obra: Obra) {
        val bundle = Bundle()
        bundle.putSerializable("obra", obra)

        view?.findNavController()?.navigate(R.id.carouselFragment, bundle)
    }
}