package com.adgonu.broadcastreceiver1.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

class MyBroadcastReceiver: BroadcastReceiver() {

    private lateinit var context: Context

    /** Esto recibe la cadena de la actividad principal **/
    override fun onReceive(p0: Context?, p1: Intent?) {
        context = p0!!
        val data = p1!!.getStringExtra("dataBroadcastReceiver1")
        if(data != null) {
            sendMessage(data)
        }
    }

    private fun sendMessage( message: String){
        val intent = Intent("msg")
        intent.putExtra("Msg", message)
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
    }

    /** Objeto con una acción que será compartida por toda la clase **/
    companion object {
        val MY_RECEIVER_ACTION = MyBroadcastReceiver::class.java.canonicalName + ".ACTION_RECEIVER"
    }
}