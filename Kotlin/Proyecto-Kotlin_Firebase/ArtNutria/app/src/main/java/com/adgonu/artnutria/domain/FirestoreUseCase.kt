package com.adgonu.artnutria.domain

import android.content.Context
import android.net.Uri
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.data.modelo.Usuario
import com.adgonu.artnutria.data.repo.FirebaseRepo

class FirestoreUseCase {
    val repo = FirebaseRepo()

    fun addUserFirestore(descripcion: String, email: String, imagen: String, nombre: String, idioma: String, favoritos: ArrayList<String>, obrasFollow: ArrayList<String>, contexto: Context){
        repo.addUser(descripcion,email,imagen,nombre, idioma, favoritos, obrasFollow, contexto)
    }

    fun addImage(imagen_url: Uri, uid: String, contexto: Context){
        repo.addImage(imagen_url,uid,contexto)
    }

    fun getIdUser(): String{
        val uid = repo.getIdUser()
        return uid
    }

    suspend fun getUser(id: String, contexto: Context): Usuario? {
        val user = repo.getUser(id, contexto)
        return user
    }

    fun getMyUserEmail(): String {
        val email = repo.getMyUserEmail()
        return email
    }

    suspend fun getIdUserName(name: String, contexto: Context): Usuario?{
        val user = repo.getIdUserName(name, contexto)
        return user
    }

    fun updateUserFollow(id: String, dato: ArrayList<String>, contexto: Context){
        repo.updateUserFollow(id, dato, contexto)
    }

    fun updateUserFavorite(id: String, dato: ArrayList<String>, contexto: Context){
        repo.updateUserFavorite(id, dato, contexto)
    }

    fun addObra(uid: String, nombre: String, imagenes: ArrayList<Uri>, etiquetas: ArrayList<String>, contexto: Context){
        val obra = repo.addObra(uid, nombre, imagenes, etiquetas, contexto)
        return obra
    }

    suspend fun getAllObras(contexto: Context): MutableList<Obra> {
        val listaObra = repo.getAllObras(contexto)
        return listaObra
    }

    suspend fun getObra(id: String, contexto: Context): Obra? {
        val obra = repo.getObra(id, contexto)
        return obra
    }

    suspend fun getObrasUser(uid: String, contexto: Context): MutableList<Obra>{
        val listaObra = repo.getObrasUser(uid, contexto)
        return listaObra
    }

    fun updateObraFollow(id: String, dato: Int, contexto: Context){
        repo.updateObraFollow(id, dato, contexto)
    }

}