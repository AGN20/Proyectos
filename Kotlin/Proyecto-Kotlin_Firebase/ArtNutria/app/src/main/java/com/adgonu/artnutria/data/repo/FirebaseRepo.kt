package com.adgonu.artnutria.data.repo

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.navigation.findNavController
import com.adgonu.artnutria.R
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.data.modelo.Usuario
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await

class FirebaseRepo {
    //Base de datos de Firebase
    val db = FirebaseFirestore.getInstance()
    val aut = FirebaseAuth.getInstance()
    val ds = FirebaseStorage.getInstance()

    /** USUARIO **/
    var user  = Usuario("","","","","", "",  ArrayList<String>(), ArrayList<String>())

    //Si no existen un Usuario igual, se creara el Usuario con los datos, si existe, modifica los datos del Usuario
    fun addUser(descripcion: String, email: String, imagen: String, nombre: String, idioma: String, favoritos: ArrayList<String>, obrasFollow: ArrayList<String>, contexto: Context){
        var id = getIdUser()
        user.id = id
        if(descripcion.isNotEmpty()){user.descripcion = descripcion}
        if(email.isNotEmpty()){user.email = email}
        if(imagen.isNotEmpty()){user.imagen = imagen}
        if(nombre.isNotEmpty()){user.nombre = nombre}
        if(idioma.isNotEmpty()){user.idioma = idioma}
        if(favoritos.isNotEmpty()){user.favoritos = favoritos}
        if(obrasFollow.isNotEmpty()){user.obrasLikkes = obrasFollow}

        db.collection(contexto.getString(R.string.pathUsers)).document(id).set(user).addOnCompleteListener {
            if (it.isSuccessful){
                Toast.makeText(contexto, contexto.getString(R.string.guardado), Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(contexto, contexto.getString(R.string.noGuardado), Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getMyUserEmail(): String {
        var usuario = aut.currentUser
        var email = usuario!!.email
        return email!!
    }

    //Optenemos la id de nuestro usuario
    fun getIdUser(): String {
        var id = aut.currentUser?.uid.toString()
        return id
    }

    //Optenemos el id de el usuario a trabes de su nombre
    suspend fun getIdUserName(name: String, contexto: Context): Usuario?{

        val consulta = db.collection(contexto.getString(R.string.pathUsers)).whereEqualTo(contexto.getString(R.string.nombre), name)
        return try {
            val consultaSnap = consulta.get().await()
            if(!consultaSnap.isEmpty){
                val docuConsulta = consultaSnap.documents[0]
                docuConsulta.toObject(Usuario::class.java)
            }else{
                null
            }
        } catch (e: Exception){
            null
        }

    }

    //Sacamos la informacion del usuario con el id correspondiente
    suspend fun getUser(id: String, contexto: Context): Usuario? = withContext(Dispatchers.IO){

        val result = db.collection(contexto.getString(R.string.pathUsers)).document(id).get().await()
        if(result.exists()){
            val user = result.toObject(Usuario::class.java)
            user
        }else{
            null
        }


    }

    //Añadir la imagen del usuario
    fun addImage(imagen_url: Uri, uid: String, contexto: Context){
        val reference = ds.reference.child(contexto.getString(R.string.userImage)).child(uid + contexto.getString(R.string.coletillaUserImage) )
        reference.putFile(imagen_url!!).addOnSuccessListener {
            reference.downloadUrl.addOnSuccessListener { uri ->
                db.collection(contexto.getString(R.string.pathUsers)).document(uid).update(contexto.getString(R.string.imagen), uri.toString()).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(contexto, contexto.getString(R.string.imagenGuardado), Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(contexto, contexto.getString(R.string.imagenNoGuardado), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    //Modificar las obras a las que as dado follow
    fun updateUserFollow(id: String, dato: ArrayList<String>, contexto: Context){

        db.collection(contexto.getString(R.string.pathUsers)).document(id).update(contexto.getString(R.string.obrasLikkes), dato)

    }

    //Modificar los usuarios seguidos
    fun updateUserFavorite(id: String, dato: ArrayList<String>, contexto: Context){

        db.collection(contexto.getString(R.string.pathUsers)).document(id).update(contexto.getString(R.string.favoritos), dato)

    }


    /** OBRA **/

    var obra = Obra("", "", "", ArrayList<String>(), ArrayList<String>(), "", 0)

    //Añadir Obra
    fun addObra(uid: String, nombre: String, imagenes: ArrayList<Uri>, etiquetas: ArrayList<String>, contexto: Context){

        var fecha = System.currentTimeMillis().toString()
        var id = uid + nombre
        var arrayListObras = ArrayList<String>()

        db.collection(contexto.getString(R.string.pathObras)).document(id).get().addOnSuccessListener { result ->

            if (result.exists()){

                var ejecucion = GlobalScope.launch(Dispatchers.IO) {
                    addImageObra(imagenes, id, contexto)
                }

            }else{

                if(id.isNotEmpty()){obra.id = id}
                if(uid.isNotEmpty()){obra.uid = uid}
                if(fecha.isNotEmpty()){obra.fecha = fecha}
                if(nombre.isNotEmpty()){obra.nombre = nombre}
                if(etiquetas.isNotEmpty()){obra.etiquetas = etiquetas}

                db.collection(contexto.getString(R.string.pathObras)).document(id).set(obra).addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(contexto, contexto.getString(R.string.obraSubida), Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(contexto, contexto.getString(R.string.obraNoSubida), Toast.LENGTH_SHORT).show()
                    }
                }

                var ejecucion = GlobalScope.launch(Dispatchers.IO) {
                    addImageObra(imagenes, id, contexto)
                }

            }

        }

    }

    //Cuando subo la imagen, hay un probla si intentas subir varias a la vez
    suspend fun addImageObra(imagens_url: ArrayList<Uri>, id: String, contexto: Context){
        //Añadimos la imagen
        var urls = mutableListOf<String>()
        var num_image = 0
        var contextResolve = contexto.contentResolver

        var obra = getObra(id, contexto)

        if(obra!!.imagenes.isNotEmpty()){
            urls = obra!!.imagenes
        }

        for(image in imagens_url){

            num_image += 1
            var id_obra = id + "_" + num_image

            val reference = ds.reference.child(contexto.getString(R.string.artstorage)).child(id_obra + contexto.getString(R.string.muletillaObra) + contextResolve.getType(image))
            reference.putFile(image).await()
            val url = reference.downloadUrl.await().toString()
            urls.add(url)
        }

        db.collection(contexto.getString(R.string.pathObras)).document(id).update(contexto.getString(R.string.imagenes), urls)

    }

    //Optener Obra por id
    suspend fun getObra(id: String, contexto: Context): Obra? = withContext(Dispatchers.IO){
        val result = db.collection(contexto.getString(R.string.pathObras)).document(id).get().await()
        if(result.exists()){
            val obra = result.toObject(Obra::class.java)
            obra
        }else{
            null
        }
    }

    //Optener todas las obras
    suspend fun getAllObras(contexto: Context): MutableList<Obra> = withContext(Dispatchers.IO){
        var listaObra = mutableListOf<Obra>()

        db.collection(contexto.getString(R.string.pathObras)).get().await().forEach {resutado ->
            val obra = resutado.toObject(Obra::class.java)
            listaObra.add(obra)
        }
        listaObra
    }

    //Optener las obras de un usuario
    suspend fun getObrasUser(uid: String, contexto: Context): MutableList<Obra> = withContext(Dispatchers.IO){
        var listaObra = mutableListOf<Obra>()

        db.collection(contexto.getString(R.string.pathObras)).whereEqualTo(contexto.getString(R.string.uid), uid).get().await().forEach { resultado ->
            val obra = resultado.toObject(Obra::class.java)
            listaObra.add(obra)
        }
        listaObra
    }

    //Updatear la obra de un usuario
    fun updateObraFollow(id: String, dato: Int, contexto: Context){

        db.collection(contexto.getString(R.string.pathObras)).document(id).update(contexto.getString(R.string.likkes), dato)

    }
}