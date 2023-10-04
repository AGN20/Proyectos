package com.adgonu.broadcastreceiver1.view

import android.Manifest
import android.app.*
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.broadcastreceiver1.R
import com.adgonu.broadcastreceiver1.adapter.RecyclerViewAdapter
import com.adgonu.broadcastreceiver1.databinding.ActivityMainBinding
import com.adgonu.broadcastreceiver1.model.Mensaje
import com.adgonu.broadcastreceiver1.notification.CHANNEL_ID
import com.adgonu.broadcastreceiver1.notification.CHANNEL_NAME
import com.adgonu.broadcastreceiver1.notification.NOTIFICATION_ID
import com.adgonu.broadcastreceiver1.notification.PENDING_REQUEST
import com.adgonu.broadcastreceiver1.receiver.MyBroadcastReceiver
import com.adgonu.broadcastreceiver1.viewmodel.MensajeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : AppCompatActivity() {

    /** Declaramos las variables a usar, las iniciaremos más tarde **/
    private lateinit var binding: ActivityMainBinding
    private lateinit var  broadcastReceiver: MyBroadcastReceiver
    private lateinit var recyclerView: RecyclerView
    private lateinit var mensajeViewModel: MensajeViewModel
    private lateinit var context: MainActivity
    private lateinit var adaptador: RecyclerViewAdapter

    /** MutableList de mensajes **/
    private var mensaje: MutableList<Mensaje> = mutableListOf()

    /** PendingIntem de las notifications **/
    private val pendingIntent: PendingIntent by lazy { createPendingIntent() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        context = this

        /** Instanciamos el viewModel **/
        mensajeViewModel = ViewModelProvider(this)[MensajeViewModel::class.java]
        mensajeViewModel.getAllMensajes()

        /** Instanciamos el BroadcastReciver para recibir los datos de la otra aplicación  **/
        broadcastReceiver = MyBroadcastReceiver()
        adaptador = RecyclerViewAdapter(mensaje)

        /** Creamos una instancia de recyclerView para cambiar scvMesajes en el layaut activity_mayn **/
        recyclerView = binding.scvMensajes
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adaptador


        /** Ponemos el mensaje en el recyclerView **/
        mensajeViewModel.rvLiveData.observe(this, androidx.lifecycle.Observer{
            mensaje.clear()
            mensaje.addAll(it)
            recyclerView.adapter?.notifyDataSetChanged()
        })

        /** Asignamos la acción al intentFilter **/
        val intentFilter = IntentFilter("com.pkg.perform.Ruby")
        if (intentFilter != null) {
            registerReceiver(broadcastReceiver, intentFilter)
        }

        getMessage()

        /** Función de botón, enviamos el mensaje al viewModel **/
        binding.btnEnviar.setOnClickListener { sendMessage() }

        /** Colocamos el mensaje recibido en el recyclerView **/
        LocalBroadcastManager.getInstance(this).registerReceiver(
            mensajeReceiver,
            IntentFilter("msg")
        )

    }

    /** Función que mostrará el mensaje que hemos escrito y lo enviará **/
    private fun sendMessage() {
        /** Si el txWrite no está vacío **/
        if(!binding.tvEscribir.text.toString().isEmpty()){
            /** Creamos el mensje**/
            var message = Mensaje(binding.tvEscribir.text.toString(), Date(), true)
            /** Enviamos el mensaje a la viewModel **/
            mensajeViewModel.sendMensaje(message)
            /** función para cerrar el teclado si cambia el enfoque **/
            hideKeyboard()
            /** Enviamos mensaje a la otra aplicación **/
            Intent().also { intent ->
                intent.setAction("com.pkg.perform.Ruby")
                intent.putExtra("dataBroadcastReceiver2", binding.tvEscribir.text.toString())
                intent.addFlags(Intent.FLAG_EXCLUDE_STOPPED_PACKAGES)
                applicationContext.sendBroadcast(intent)
            }
            /** Ponemos en blanco la tvWrite **/
            binding.tvEscribir.setText("")
        }else{
            /** Toast cunado el mensaje esta vacio **/
            Toast.makeText(this, "you have no written message", Toast.LENGTH_SHORT).show()
        }
    }

    /** Función que cierra el teclado cuando quitamos el foco **/
    private fun Context.hideKeyboard(){
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    /** Función para leer los mensajes **/
    private fun getMessage(){
        var receiver = MyBroadcastReceiver()
        val intentFilter = IntentFilter(MyBroadcastReceiver.MY_RECEIVER_ACTION)
        registerReceiver(receiver, intentFilter)
    }

    /** Función que recibe el mensaje de la otra aplicación **/
    private val mensajeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(p0: Context, p1: Intent?) {
            val message = p1?.getStringExtra("Msg")
            var msg = Mensaje(message.toString(), Date(), false)
            mensajeViewModel.sendMensaje(msg)
            sendNotification(msg.string)
        }
    }

    /** NOTIFICACIONES **/

    /** Función que crea la notificación **/
    private fun sendNotification(string: String){
        CoroutineScope(Dispatchers.Default).launch {
            delay(400L) //time in milliseconds for waiting for notification to be displayed
            createChanelNotifications() // Create channel for notification
            createNotifications(string) // Create notification
        }
    }

    /** Función que crea un canal de notificación **/
    private fun createChanelNotifications() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            /** Los datos para ello se tomarán de  \notification\Notificacion.kt **/
            val notificationChannel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }

    /** Creamos la notificación que saldrá cuando la otra aplicación nos envíe un mensaje **/
    private fun createNotifications(string: String) {
        val builder: NotificationCompat.Builder = NotificationCompat.Builder(
            this,
            CHANNEL_ID
        )

        with(builder){
            setSmallIcon(R.drawable.chat1) //Icon
            setContentTitle("Aplicacion 2").color = Color.BLUE //Color
            var cleanMesage = string?.substring(string.indexOf('\n') + 1)

            setStyle( NotificationCompat.BigTextStyle().bigText(cleanMesage))

            setAutoCancel(true) //Function that removes the notification if you touch it

            priority = NotificationCompat.PRIORITY_DEFAULT //Priority

            setLights(Color.BLUE, 1000, 2000) //Time of the lights

            setVibrate(longArrayOf(1000, 1000, 2000, 2000)) //Time of the vibrate

            setDefaults(Notification.DEFAULT_SOUND) //Sound

            setContentIntent(pendingIntent)

            setVisibility(NotificationCompat.VISIBILITY_PUBLIC) //todos pueden ver la notificación

            setFullScreenIntent(pendingIntent, true)
        }

        val notificationManagerCompat = NotificationManagerCompat.from(this) //Crea la notification

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }else{
            notificationManagerCompat.notify(NOTIFICATION_ID, builder.build()) //Lanza la notificación
        }


    }

    /** Función de crear una intención en esta clase **/
    private fun createPendingIntent(): PendingIntent{
        val intent = Intent(this, MainActivity::class.java)

        return PendingIntent.getActivity(
            this,
            PENDING_REQUEST,
            intent,
            PendingIntent.FLAG_MUTABLE
        )
    }


}