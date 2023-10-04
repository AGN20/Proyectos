package com.adgonu.broadcastreceiver1.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adgonu.broadcastreceiver1.model.Mensaje
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MensajeViewModel(): ViewModel() {

    val rvLiveData: MutableLiveData<MutableList<Mensaje>> = MutableLiveData()
    var listaMensaje: ArrayList<Mensaje> = ArrayList<Mensaje>()

    /** Insertamos el mensaje dentro de la mutableList **/
    fun sendMensaje(mensaje: Mensaje){
        listaMensaje.add(mensaje)
        rvLiveData.postValue(listaMensaje)
    }

    /** Cogemos los mensajes del mutableList **/
    fun getAllMensajes(){
        CoroutineScope(Dispatchers.IO).launch {
            rvLiveData.postValue(listaMensaje)
        }
    }

    /** Constructo de la clase **/
    init{
        rvLiveData.value = listaMensaje
    }
}