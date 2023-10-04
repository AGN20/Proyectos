package com.adgonu.artnutria.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
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
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.launch

class UserFragment : Fragment(), OnItemClickListenerObra {

    private lateinit var viewModel: FirebaseViewModel
    private var usuario: Usuario? = null

    lateinit var obraList: ArrayList<Obra>
    lateinit var adapter: ObraAdapter
    lateinit var rvMyObra: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        arguments?.let {

            usuario = it.getSerializable(context?.getString(R.string.user)) as Usuario

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        updateContent(view)
        setup(view)
        changeButtonSeguir(view)

    }

    private fun setup(view: View) {
        var nombre: TextView = view.findViewById(R.id.txNombre)
        var email: TextView = view.findViewById(R.id.txEmail)
        var idioma: TextView = view.findViewById(R.id.txIdioma)
        var descripcion: TextView = view.findViewById(R.id.txDescripcion)
        var image: CircleImageView = view.findViewById(R.id.ivUser)

        nombre.text = usuario!!.nombre
        email.text = usuario!!.email
        idioma.text = usuario!!.idioma
        descripcion.text = usuario!!.descripcion
        Picasso.get().load(usuario!!.imagen).into(image)
    }

    private fun updateContent(view: View) {

        obraList = arrayListOf<Obra>()

        rvMyObra = view.findViewById(R.id.rvObrasUser)
        rvMyObra.setHasFixedSize(true)
        rvMyObra.visibility = View.VISIBLE
        rvMyObra.layoutManager = GridLayoutManager(requireContext(), 3)

        lifecycleScope.launch{
            obraList = viewModel.getObrasUser(usuario!!.id, view.context) as ArrayList<Obra>

            if(obraList.size != 0){
                adapter = ObraAdapter(obraList.sortedByDescending { it.fecha }, this@UserFragment)

                rvMyObra.adapter = adapter
            }

        }

    }

    // Funcion boton seguir, añade el usuario a los seguidos y modifica el boton ( si ya se sigue, lo deja de seguir y modifica el boton )
    private fun changeButtonSeguir(view: View) {

        var btSeguir: Button = view.findViewById(R.id.btnSeguirUser)

        var change = lifecycleScope.launch{

            var myUserID = viewModel.getIdUser()
            var myUser = viewModel.getUser(myUserID, view.context)

            var controllSeguir = false

            if(myUser!!.id == usuario?.id || myUser.email == context?.getString(R.string.NotUser) ){
                btSeguir.isEnabled = false
            }

            for(seguir in myUser!!.favoritos){
                if(seguir == usuario!!.id){
                    btSeguir.text = context?.getString(R.string.NoSeguir)
                    controllSeguir = true
                    break
                }
            }

            //Cambiamos el boton dependiendo si lo seguimos o no
            btSeguir.setOnClickListener{
                if(controllSeguir == false) {
                    btSeguir.text = context?.getString(R.string.NoSeguir)
                    myUser.favoritos.add(usuario!!.id)
                    FirebaseMessaging.getInstance().subscribeToTopic(usuario!!.id)
                    viewModel.updateUserFavorite(myUser.id, myUser.favoritos, view.context)
                    controllSeguir = true
                }else{
                    btSeguir.text = context?.getString(R.string.Seguir)
                    myUser.favoritos.remove(usuario!!.id)
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(usuario!!.id)
                    viewModel.updateUserFavorite(myUser.id, myUser.favoritos, view.context)
                    controllSeguir = false
                }
            }

        }

    }

    fun setArguments(user: Usuario){
        arguments = Bundle().apply {
            putSerializable(context?.getString(R.string.user), user)
        }
    }

    override fun onItemClick(obra: Obra) {
        val bundle = Bundle()
        bundle.putSerializable(context?.getString(R.string.obra), obra)

        view?.findNavController()?.navigate(R.id.carouselFragment, bundle)
    }
}