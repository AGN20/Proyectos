package com.example.p6_consuming_internet_api_rest

import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.p6_consuming_internet_api_rest.Adapter.MonsterAdapter
import com.example.p6_consuming_internet_api_rest.Response.Monster
import com.example.p6_consuming_internet_api_rest.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    //ReciclerView sin Iniciar
    lateinit var rvMonsters: RecyclerView

    //Variables que contienen el contexto
    var contexto: Context = this
    //Variable que contiene la lista de Monstruos
    lateinit var monsterList: ArrayList<Monster>
    //Variable que contiene el adaptador
    lateinit var adapter: MonsterAdapter

    //Configuramos el Binding
    private lateinit var binding: ActivityMainBinding

    //Creación al inicio de la aplicación
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Iniciamos metemos el binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Lista de Monstruos
        monsterList = ArrayList<Monster>()

        //Iniciamos el RecicleView
        rvMonsters = binding.rvMonsters
        rvMonsters.setHasFixedSize(true)
        rvMonsters.visibility = View.VISIBLE
        rvMonsters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)

        //Introducimos el adapteador con su contexto y la lista de monstruos
        adapter = MonsterAdapter(contexto, monsterList)
        //Le metemos el adaptador creado al adaptador del ReciclerView
        rvMonsters.adapter = adapter

        //llamamos al metodo getMonster que hemos definido abajo
        getMonster()
    }

    fun getMonster() {

        //Limpiamos la lista por si acaso
        monsterList.clear()

        //Creamos la variable que contiene la cola de Volley
        var queue = Volley.newRequestQueue(contexto)
        //API que vamos a utilizar (en mi caso los monstruos de Hyrule -> https://botw-compendium.herokuapp.com/api/v2/category/monsters)
        val url = "https://botw-compendium.herokuapp.com/api/v2/category/monsters"

        //Creamos la variable que contendra nuestra array de Json
        val jar = JsonObjectRequest(
            //Utilizamos el metodo get
            Request.Method.GET,
            //Le pasamos la url de nuestra API
            url,
            null,
            { response ->
                //Sacamos la Array de los objetos
                var monsters = response.getJSONArray("data")
                for (i in 0 until monsters.length()){
                    //Sacamos lo que queremos de los monstruos
                    var monstruo = monsters.getJSONObject(i)
                    //Sacamos los campos que queremos de los monstruos
                    var name = monstruo.getString("name")
                    var description = monstruo.getString("description")
                    var location = monstruo.getString("common_locations").removePrefix("[").removeSuffix("]")
                    var drops = monstruo.getString("drops").removePrefix("[").removeSuffix("]")
                    var image: String? = null
                    //en principio la api contiene todas las imagenes, pero por si acaso, miramos si tiene imagen y si no, nos muestra una imagen de error
                    try{
                        image = monstruo.getString("image")
                    }catch(e: Exception){
                        image = "https://static.vecteezy.com/system/resources/previews/004/141/669/non_2x/no-photo-or-blank-image-icon-loading-images-or-missing-image-mark-image-not-available-or-image-coming-soon-sign-simple-nature-silhouette-in-frame-isolated-illustration-vector.jpg"
                    }
                    //Introducimos al MonsterResponse lo que hemos sacado
                    var mf: Monster = Monster(location,description,drops,image!!,name)
                    //Y lo añadimos a nuestra lista de monstruos
                    monsterList.add(mf)
                }
                //Notificamos que se a cambiado el contenido
                adapter.notifyDataSetChanged()
            },
            {error ->
                Toast.makeText(contexto, "Error: " + error.message, Toast.LENGTH_LONG).show()
            })

        //añadimos la array de Json a nuestra cola
        queue.add(jar)
    }
}