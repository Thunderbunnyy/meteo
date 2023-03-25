package com.nourelhoudaeleuch.meteo.data.repository

import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.database.entity.Main

interface WeatherRepository {
    //asynchronous : suspend coroutine
    suspend fun getCurrentWeather() : LiveData<out Main>
}