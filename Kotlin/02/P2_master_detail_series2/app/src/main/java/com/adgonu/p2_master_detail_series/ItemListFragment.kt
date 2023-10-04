package com.adgonu.p2_master_detail_series

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.p2_master_detail_series.databinding.FragmentItemListBinding
import com.adgonu.p2_master_detail_series.databinding.ItemListContentBinding
import com.adgonu.p2_master_detail_series.model.Serie
import com.adgonu.p2_master_detail_series.model.Serie.Companion.toBitmap

/**
 * A Fragment representing a list of Pings. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link ItemDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */

class ItemListFragment : Fragment() {

    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    private val unhandledKeyEventListenerCompat =
        ViewCompat.OnUnhandledKeyEventListenerCompat { v, event ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            false
        }

    private var _binding: FragmentItemListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = "Series List" //Le ponemos el titulo de la aplicación
        _binding = FragmentItemListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)

        val recyclerView: RecyclerView = binding.itemList

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        setupRecyclerView(recyclerView, itemDetailFragmentContainer)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?
    ) {

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            Serie.loadSeries(requireContext()), //Cambiamos, le ponemos nuestro modelo para que lea con el load
            itemDetailFragmentContainer
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val values: MutableList<Serie>?,//El setupReciclerView te fallara si esto no lo cambias a al tipo MutableList de tu clase
        private val itemDetailFragmentContainer: View?
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ItemListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values?.get(position)!!

            //Cambia para que coja los datos que necesitas y los muestre
            holder.nameView.text = item.name
            holder.lenguageView.text = item.language
            holder.imageView.findViewById<ImageView>(R.id.ivImage).setImageBitmap(item?.image?.toBitmap(holder.imageView.context))
            holder.starView.rating = item.rating / 2

            //En esta parte dependiendo del estado de la serie, se pondra un color de fondo o otro
            if(item.status == "Ended"){
                holder.cardView.setBackgroundColor(Color.parseColor("#AEAD97"))
                holder.constraintLayout.setBackgroundColor(Color.parseColor("#BFC4AB"))
            }else{
                holder.cardView.setBackgroundColor(Color.parseColor("#8ABC97"))
                holder.constraintLayout.setBackgroundColor(Color.parseColor("#9BD5AC"))
            }

            with(holder.itemView) {
                tag = item
                setOnClickListener { itemView ->
                    val item = itemView.tag as Serie
                    val bundle = Bundle()
                    bundle.putString(
                        ItemDetailFragment.ARG_ITEM_ID,
                        item.id.toString()
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_item_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    }
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    /**
                     * Context click listener to handle Right click events
                     * from mice and trackpad input to provide a more native
                     * experience on larger screen devices
                     */
                    setOnContextClickListener { v ->
                        val item = v.tag as Serie
                        Toast.makeText(
                            v.context,
                            "Context click of item " + item.id,
                            Toast.LENGTH_LONG
                        ).show()
                        true
                    }
                }
            }
        }

        override fun getItemCount() = values?.size ?: 0

        //Para añadir nuestros datos, insertamos aqui el elemento del layaut al que se lo vamos a añadir
        inner class ViewHolder(binding: ItemListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val nameView: TextView = binding.tvTitle
            val lenguageView: TextView = binding.tvLenguaje
            val imageView: ImageView = binding.ivImage
            val starView: RatingBar = binding.ratingBar
            val cardView: CardView = binding.cardView
            val constraintLayout: ConstraintLayout = binding.constraintLayout
        }

    }

}