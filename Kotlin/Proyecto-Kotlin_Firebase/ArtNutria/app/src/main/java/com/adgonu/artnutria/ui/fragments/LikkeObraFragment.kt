package com.adgonu.artnutria.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.Adapter.ObraAdapter
import com.adgonu.artnutria.data.Interface.OnItemClickListenerObra
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.viewmodel.FirebaseViewModel
import kotlinx.coroutines.launch


class LikkeObraFragment : Fragment(), OnItemClickListenerObra {

    private lateinit var viewModel: FirebaseViewModel

    lateinit var rvObra: RecyclerView
    lateinit var obraList: ArrayList<Obra>
    lateinit var adapter: ObraAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_likke_obra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar: ProgressBar = view.findViewById(R.id.progressBarLikke)
        progressBar.visibility = View.VISIBLE

        viewModel = FirebaseViewModel()

        obraList = arrayListOf<Obra>()

        rvObra = view.findViewById(R.id.rvFollowObra)
        rvObra.setHasFixedSize(true)
        rvObra.visibility = View.VISIBLE
        rvObra.layoutManager = GridLayoutManager(requireContext(), 3)

        lifecycleScope.launch{

            var myUser = viewModel.getUser(viewModel.getIdUser(), view.context)!!
            for(idObra in myUser.obrasLikkes){
                var obra = viewModel.getObra(idObra, view.context)!!
                obraList.add(obra)
            }

            adapter = ObraAdapter(obraList.sortedByDescending { it.fecha }, this@LikkeObraFragment)

            rvObra.adapter = adapter
            progressBar.visibility = View.GONE
        }

    }

    override fun onItemClick(obra: Obra) {

        val bundle = Bundle()
        bundle.putSerializable(context?.getString(R.string.obra), obra)

        view?.findNavController()?.navigate(R.id.carouselFragment, bundle)

    }

}