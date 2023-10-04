package com.adgonu.p2_master_detail_series

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import com.adgonu.p2_master_detail_series.databinding.FragmentItemDetailBinding
import com.adgonu.p2_master_detail_series.model.Serie
import com.adgonu.p2_master_detail_series.model.Serie.Companion.toBitmap
import com.google.android.material.snackbar.Snackbar

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ItemListFragment]
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
class ItemDetailFragment : Fragment() {

    /**
     * The placeholder content this fragment is presenting.
     */
    private var item: Serie? = null

    //Elementos que utilizaremos

    lateinit var itemDetailTextView: TextView
    lateinit var tvGenresTextView: TextView
    lateinit var tvLanguageTextView: TextView
    lateinit var tvUrlTextView: TextView
    lateinit var tvPrimeredTextView: TextView
    lateinit var image_view: ImageView

    private var toolbarLayout: CollapsingToolbarLayout? = null

    private var _binding: FragmentItemDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var condicionVisible:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                // Load the placeholder content specified by the fragment
                // arguments. In a real-world scenario, use a Loader
                // to load content from a content provider.
                var id = it.getString(ARG_ITEM_ID)!!
                if(id != ""){
                    item = Serie.getSerieBiId(id.toInt())
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Details" //Le ponemos el titulo de la aplicación
        _binding = FragmentItemDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root

        //Enlazamos los componentes del layaut, para poder utilizarlos
        toolbarLayout = binding.toolbarLayout
        itemDetailTextView = binding.itemDetail
        tvGenresTextView = binding.tvGenres!!
        tvLanguageTextView = binding.tvLanguage!!
        tvUrlTextView = binding.tvUrl!!
        tvPrimeredTextView = binding.tvPrimered!!
        //Si no hay toolbar, se añade el imageView
        if(toolbarLayout == null) {
            image_view = binding.imageView!!
        }


        //Indicamos que cuando se clique el boton (i) se iniciara un mensaje con un boton aceptar, si le damos se mostrara mas informacion, si le volvemos a dar se ocultara
        binding.fab?.setOnClickListener{
            val mySnackbar = Snackbar.make(it, "Press accept for more info", Snackbar.LENGTH_LONG).setAction("ACCEPT"){

                if(condicionVisible == false){
                    tvGenresTextView.visibility = View.VISIBLE
                    tvLanguageTextView.visibility = View.VISIBLE
                    tvPrimeredTextView.visibility = View.VISIBLE
                    tvUrlTextView.visibility = View.VISIBLE
                    condicionVisible = true
                }else{
                    tvGenresTextView.visibility = View.INVISIBLE
                    tvLanguageTextView.visibility = View.INVISIBLE
                    tvPrimeredTextView.visibility = View.INVISIBLE
                    tvUrlTextView.visibility = View.INVISIBLE
                    condicionVisible = false
                }

            }
            mySnackbar.show()
        }

        updateContent()

        return rootView
    }

    private fun updateContent() {
        //Metemos los datos
        toolbarLayout?.title = item?.name

        //Dependiendo de si tenemos un Toolbarlayaut o un ImageView cambiaremos al elemento que le vamos a añadir la imagen.
        if(toolbarLayout != null){
            var image = item!!.image.toBitmap(toolbarLayout!!.context)
            toolbarLayout?.background = image.toDrawable(resources)
        }else{
            if(item?.image != null){
                var image = item!!.image.toBitmap(image_view!!.context)
                image_view?.background = image.toDrawable(resources)
            }
        }

        //En esta parte rellenearemos los datos del layaut
        // Show the placeholder content as text in a TextView.
        item?.let {
            itemDetailTextView.text = it.summary
            tvGenresTextView.text = "Genres:\n" + item?.genres?.joinToString()
            tvLanguageTextView.text = "Language:\n" + item?.language
            tvPrimeredTextView.text = "Primered time:\n" + item?.premiered
            tvUrlTextView.text = "URL:\n" + item?.officialSite
            //Indicamos que tvUrlTextView al cliquearlo iniciara la funcion getUrlFromIntent
            tvUrlTextView.setOnClickListener { buscarUrlInternet(item!!.officialSite) }
        }

    }



    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }

    //Este parte del codigo nos permite abrir el explorador y buscar la url que se le pase, es necesario hacer doble clic
    fun buscarUrlInternet(url: String){
        val intent = Intent(Intent.ACTION_WEB_SEARCH).apply {
            putExtra(SearchManager.QUERY, url)
        }
        startActivity(intent)
    }

}