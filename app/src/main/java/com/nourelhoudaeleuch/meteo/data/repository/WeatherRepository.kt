package com.nourelhoudaeleuch.meteo.data.repository

import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.database.entity.*
import com.nourelhoudaeleuch.meteo.data.responses.CurrentWeatherByCityResponse

interface WeatherRepository {
    //asynchronous : suspend coroutine
    suspend fun getTemperature() : LiveData<out Main>
    suspend fun getClouds() : LiveData<out Clouds>
    suspend fun getWind() : LiveData<out Wind>
    suspend fun getWeather() : LiveData<out Weather>
    suspend fun getLocation() : LiveData<out Coord>

}