package com.adgonu.artnutria.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.Adapter.ImageAdapter
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.data.modelo.Usuario
import com.adgonu.artnutria.viewmodel.FirebaseViewModel
import com.google.firebase.messaging.FirebaseMessaging
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.launch

class CarouselFragment : Fragment() {

    private lateinit var viewModel: FirebaseViewModel

    private var obra: Obra? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FirebaseViewModel::class.java)
        arguments?.let {

            obra = it.getSerializable(context?.getString(R.string.obra)) as Obra

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_carousel, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as AppCompatActivity).supportActionBar?.show()

        var userImage: CircleImageView = view.findViewById(R.id.ivCarUsuario)
        var nombreUser: TextView = view.findViewById(R.id.tvCarNombre)
        var nombreObra: TextView = view.findViewById(R.id.tvCarObra)
        var numFollows: TextView = view.findViewById(R.id.tvNumFollow)
        var numMedia: TextView = view.findViewById(R.id.tvNumeroImagenes)
        var botSeguir: Button = view.findViewById(R.id.bttnCarSeguir)
        var followImage: CircleImageView = view.findViewById(R.id.ivCarFollow)
        var carrousel: RecyclerView = view.findViewById(R.id.carousel)
        var etiquetaUser: ConstraintLayout = view.findViewById(R.id.clEtiqueraUsuario)

        lifecycleScope.launch{
            var user = viewModel.getUser(obra!!.uid, view.context)

            etiquetaUser.setOnClickListener {
                changeFragmentUser(user!!)
            }

            if(user!!.imagen != ""){
                Picasso.get().load(user!!.imagen).into(userImage)
            }
            nombreUser.text = user.nombre

            // Buscamos si se le a dado follow anterior mente
            var myUser = viewModel.getUser(viewModel.getIdUser(), view.context)

            if(myUser!!.id == user.id || myUser.email == context?.getString(R.string.NotUser) ){
                botSeguir.isEnabled = false
                followImage.isEnabled = false
            }

            //Controlamos si ya le a dado follow
            var controllFollow = false

            for(obraUser in myUser!!.obrasLikkes){

                if(obra!!.id == obraUser){
                    Picasso.get().load(R.drawable.follow2).into(followImage)
                    controllFollow = true
                    break
                }

            }

            //al clicar cambiamos la imagen y actualizamos el follow de Obra y el obrasFollow del usuario
            followImage.setOnClickListener {
                if(controllFollow == false) {
                    Picasso.get().load(R.drawable.follow2).into(followImage)
                    obra!!.likkes += 1
                    viewModel.updateObraFollow(obra!!.id, obra!!.likkes, view.context)
                    myUser.obrasLikkes.add(obra!!.id)
                    viewModel.updateUserFollow(myUser.id, myUser.obrasLikkes, view.context)
                    numFollows.text = context?.getString(R.string.FormLikkes) + obra!!.likkes.toString()
                    controllFollow = true
                    val bundle = Bundle()
                    bundle.putSerializable(context?.getString(R.string.obra), obra)
                }else{
                    Picasso.get().load(R.drawable.follow).into(followImage)
                    obra!!.likkes -= 1
                    viewModel.updateObraFollow(obra!!.id, obra!!.likkes, view.context)
                    myUser.obrasLikkes.remove(obra!!.id)
                    viewModel.updateUserFollow(myUser.id, myUser.obrasLikkes, view.context)
                    numFollows.text = context?.getString(R.string.FormLikkes) + obra!!.likkes.toString()
                    controllFollow = false
                    val bundle = Bundle()
                    bundle.putSerializable(context?.getString(R.string.obra), obra)
                }
            }

            //Controlamos si ya se sigue al usuario
            var controllSeguir = false

            for(seguir in myUser.favoritos){
                if(seguir == obra!!.uid){
                    botSeguir.text = context?.getString(R.string.NoSeguir)
                    controllSeguir = true
                    break
                }
            }

            //Cambiamos el boton dependiendo si lo seguimos o no
            botSeguir.setOnClickListener{
                if(controllSeguir == false) {
                    botSeguir.text = context?.getString(R.string.NoSeguir)
                    myUser.favoritos.add(obra!!.uid)
                    FirebaseMessaging.getInstance().subscribeToTopic(obra!!.uid)
                    viewModel.updateUserFavorite(myUser.id, myUser.favoritos, view.context)
                    controllSeguir = true
                }else{
                    botSeguir.text = context?.getString(R.string.Seguir)
                    myUser.favoritos.remove(obra!!.uid)
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(obra!!.uid)
                    viewModel.updateUserFavorite(myUser.id, myUser.favoritos, view.context)
                    controllSeguir = false
                }
            }

        }

        nombreObra.text = obra!!.nombre
        numFollows.text = context?.getString(R.string.FormLikkes) + obra!!.likkes
        numMedia.text = context?.getString(R.string.FormImagenes) + obra!!.imagenes.size

        carrousel.setHasFixedSize(true)
        carrousel.visibility = View.VISIBLE
        carrousel.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        var imageAdapter = ImageAdapter(obra!!.imagenes)
        carrousel.adapter = imageAdapter


    }

    fun changeFragmentUser(user: Usuario){

        val bundle = Bundle()
        bundle.putSerializable(context?.getString(R.string.user), user)

        lifecycleScope.launch {
            var myUser = viewModel.getIdUser()
            if(user.id == myUser){
                view?.findNavController()?.navigate(R.id.myUserFragment)
            }else{
                view?.findNavController()?.navigate(R.id.userFragment, bundle)
            }
        }


    }

}