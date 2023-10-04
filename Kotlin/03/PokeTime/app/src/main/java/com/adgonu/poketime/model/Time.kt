package com.adgonu.poketime.model

import androidx.lifecycle.LiveData
import kotlinx.coroutines.*
import java.util.*

typealias  OnOrder = (order:String) -> Unit

class Time {
    val random: Random = Random()

    var weather: Job? = null

    var moment = 0
    var time = -1


    fun startWeather(onOrder: OnOrder) {
        if (weather == null || weather!!.isCancelled || weather!!.isCompleted) {
            weather = CoroutineScope(Dispatchers.IO).launch {

                while (true) {
                    if (time < 0) {
                        time = random.nextInt(5) + 3
                        moment = random.nextInt(6) + 1
                    }
                    onOrder("WEATHER" + moment + ":" + if (time == 0) "CHANGE" else time)
                    time--

                    delay(1000)
                }

            }
        }
    }

    fun stopWeather() {
        weather?.let {
            if(it.isActive)
                it.cancel()
        }
        moment = 0
        time = -1

    }

    val orderLiveData: LiveData<String> = object:LiveData<String>(){
        override fun onActive() {
            super.onActive()
            startWeather {
                    order -> postValue(order)
            }
        }

        override fun onInactive() {
            super.onInactive()
            stopWeather()
        }
    }
}