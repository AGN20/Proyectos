package com.adgonu.broadcastreceiver2.model

import java.util.*

/** Clase de datos del mensaje, tendra un texto, la fecha y un booleano para saber quien lo envio **/
data class Mensaje (val string: String, val date: Date, val boolean: Boolean)
