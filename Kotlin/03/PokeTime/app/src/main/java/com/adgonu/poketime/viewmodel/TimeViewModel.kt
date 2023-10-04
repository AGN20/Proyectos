package com.adgonu.poketime.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.adgonu.poketime.R
import com.adgonu.poketime.model.Time

class TimeViewModel(application: Application) : AndroidViewModel(application) {

    private var time: Time = Time()


    var weatherLiveData : LiveData<Int>
    var timeLiveData :LiveData<String>

    private var previousWeather:String =""


    init {

        weatherLiveData = Transformations.switchMap(time.orderLiveData,){
                weather ->
            val mWeather = weather.split(":")[0]
            if(mWeather != previousWeather){
                previousWeather = mWeather

                var imageID:Int = when(mWeather){
                    "WEATHER1" -> R.drawable.sun
                    "WEATHER2" -> R.drawable.rain
                    "WEATHER3" -> R.drawable.viento
                    "WEATHER4" -> R.drawable.nieve
                    "WEATHER5" -> R.drawable.niebla
                    "WEATHER6" -> R.drawable.arena
                    else -> R.drawable.sun

                }
                return@switchMap MutableLiveData<Int>(imageID)
            }

            return@switchMap null
        }

        timeLiveData = Transformations.switchMap(time.orderLiveData) { weather ->
            return@switchMap MutableLiveData<String>(weather.split(":")[1])
        }



    }

}