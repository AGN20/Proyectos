package com.adgonu.artnutria.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.Adapter.UserAdapter
import com.adgonu.artnutria.data.Interface.OnItemClickListenerUsuario
import com.adgonu.artnutria.data.modelo.Usuario
import com.adgonu.artnutria.viewmodel.FirebaseViewModel
import kotlinx.coroutines.launch

class FollowUserFragment : Fragment(), OnItemClickListenerUsuario {

    private lateinit var viewModel: FirebaseViewModel

    lateinit var rvUsuario: RecyclerView
    lateinit var userList: ArrayList<Usuario>
    lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_follow_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar = view.findViewById(R.id.progressBarFollow)
        progressBar.visibility = View.VISIBLE

        viewModel = FirebaseViewModel()

        userList = arrayListOf<Usuario>()

        rvUsuario = view.findViewById(R.id.ryFollowUser)
        rvUsuario.setHasFixedSize(true)
        rvUsuario.visibility = View.VISIBLE
        rvUsuario.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        lifecycleScope.launch{

            var myUsuario = viewModel.getUser(viewModel.getIdUser(), view.context)

            //Por cada uno de los elementos sacamos el usuario y lo añadimos a la lista
            for(followUser in myUsuario!!.favoritos){
                var usuario = viewModel.getUser(followUser, view.context)
                userList.add(usuario!!)
            }

            adapter = UserAdapter(userList, this@FollowUserFragment)

            rvUsuario.adapter = adapter
            progressBar.visibility = View.GONE
        }

    }

    override fun onItemClick(user: Usuario) {
        val bundle = Bundle()
        bundle.putSerializable(context?.getString(R.string.user), user)

        view?.findNavController()?.navigate(R.id.userFragment, bundle)
    }

}