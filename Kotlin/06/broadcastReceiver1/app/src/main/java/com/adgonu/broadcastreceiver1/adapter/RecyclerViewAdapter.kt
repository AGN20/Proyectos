package com.adgonu.broadcastreceiver1.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.adgonu.broadcastreceiver1.R
import com.adgonu.broadcastreceiver1.model.Mensaje
import java.text.SimpleDateFormat

class RecyclerViewAdapter(val listaMansages: List<Mensaje>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        var txMensaje: TextView

        init{
            txMensaje = view.findViewById(R.id.txMensaje)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_view, parent, false)

        return ViewHolder(view)
    }

    /** Función para dibujar un mensaje en el viewHolder **/
    @SuppressLint("NewApi")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listM: Mensaje = listaMansages.get(position)
        var vHolder: ViewHolder = holder
        vHolder.txMensaje.text = listM.string

        //Nosotros formateamos la fecha
        var fecha = listaMansages[itemCount - 1].date.toString()

        val formatData = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val dataFormated = formatData.format(listM.date)
        fecha = dataFormated


        if(listM.boolean){ //si esto es enviado por mi

            //Configuración del texto a mostrarnos
            vHolder.txMensaje.setTypeface(null, Typeface.BOLD_ITALIC)
            vHolder.txMensaje.setText(vHolder.txMensaje.text.toString() + "\n" + fecha)
            vHolder.txMensaje.setBackgroundColor(Color.parseColor("#FFFFFFFF"))

            //Configuración de la casilla donde se mostrará el texto
            val forma = GradientDrawable()
            forma.shape = GradientDrawable.RECTANGLE
            forma.setColor(Color.parseColor("#FFFFFFFF"))
            forma.setStroke(3, Color.parseColor("#6F7072"))
            forma.setCornerRadius(15F)
            forma.setPadding(15, 15, 15, 15)
            vHolder.txMensaje.setBackgroundDrawable(forma) //Añadimos al textView

            //Parametros
            val confi: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            confi.setMargins(8, 8, 8, 8)
            confi.gravity = Gravity.RIGHT
            confi.width = 600
            vHolder.txMensaje.layoutParams = confi //Añadimos al TextView

        }else{

            //Configuración del texto a mostrarnos
            vHolder.txMensaje.setTypeface(null, Typeface.BOLD_ITALIC)
            vHolder.txMensaje.setText(vHolder.txMensaje.text.toString() + "\n" + fecha)
            vHolder.txMensaje.setBackgroundColor(Color.parseColor("#30CC04"))

            //Configuración de la casilla donde se mostrará el texto
            val forma = GradientDrawable()
            forma.shape = GradientDrawable.RECTANGLE
            forma.setColor(Color.parseColor("#30CC04"))
            forma.setStroke(3, Color.parseColor("#6F7072"))
            forma.setCornerRadius(15F)
            forma.setPadding(15, 15, 15, 15)
            vHolder.txMensaje.setBackgroundDrawable(forma) //Añadimos al textView

            //Parametros
            val confi: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            confi.setMargins(8, 8, 8, 8)
            confi.gravity = Gravity.LEFT
            confi.width = 600
            vHolder.txMensaje.layoutParams = confi //Añadimos al TextView
        }
    }

    override fun getItemCount() = listaMansages.size

}