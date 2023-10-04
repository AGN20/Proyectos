package com.adgonu.artnutria.viewmodel

import android.content.Context
import android.net.Uri
import androidx.lifecycle.ViewModel
import com.adgonu.artnutria.data.modelo.Obra
import com.adgonu.artnutria.data.modelo.Usuario
import com.adgonu.artnutria.domain.FirestoreUseCase

class FirebaseViewModel: ViewModel() {

    val firestoreUseCase = FirestoreUseCase()

    fun addUser(descripcion: String, email: String, imagen: String, nombre: String, idioma: String, favoritos: ArrayList<String>, obrasFollow: ArrayList<String>, contexto: Context){
        firestoreUseCase.addUserFirestore(descripcion,email,imagen,nombre,idioma,favoritos, obrasFollow, contexto)
    }

    fun addImage(imagen_url: Uri, uid: String, contexto: Context){
        firestoreUseCase.addImage(imagen_url,uid,contexto)
    }

    fun getIdUser(): String{
        val uid = firestoreUseCase.getIdUser()
        return uid
    }

    suspend fun getUser(id: String, contexto: Context): Usuario? {
        val user = firestoreUseCase.getUser(id, contexto)
        return user
    }

    suspend fun getIdUserName(name: String, contexto: Context): Usuario?{
        val user = firestoreUseCase.getIdUserName(name, contexto)
        return user
    }

    fun getMyUserEmail(): String {
        val email = firestoreUseCase.getMyUserEmail()
        return email
    }

    fun updateUserFollow(id: String, dato: ArrayList<String>,contexto: Context){
        firestoreUseCase.updateUserFollow(id, dato, contexto)
    }

    fun updateUserFavorite(id: String, dato: ArrayList<String>,contexto: Context){
        firestoreUseCase.updateUserFavorite(id, dato, contexto)
    }

    fun addObra(uid: String, nombre: String, imagenes: ArrayList<Uri>, etiquetas: ArrayList<String>, contexto: Context){
        val obra = firestoreUseCase.addObra(uid, nombre, imagenes, etiquetas, contexto)
        return obra
    }

    suspend fun getAllObras(contexto: Context): MutableList<Obra> {
        val listaObra = firestoreUseCase.getAllObras(contexto)
        return listaObra
    }

    suspend fun getObra(id: String, contexto: Context): Obra? {
        val obra = firestoreUseCase.getObra(id, contexto)
        return obra
    }

    suspend fun getObrasUser(uid: String, contexto: Context): MutableList<Obra>{
        val listaObra = firestoreUseCase.getObrasUser(uid, contexto)
        return listaObra
    }

    fun updateObraFollow(id: String, dato: Int,contexto: Context){
        firestoreUseCase.updateObraFollow(id, dato, contexto)
    }

}