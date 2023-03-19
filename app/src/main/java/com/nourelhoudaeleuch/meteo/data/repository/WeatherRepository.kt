package com.nourelhoudaeleuch.meteo.data.repository

import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.database.entity.CurrentWeatherEntity

interface WeatherRepository {
    //asynchronous : suspend couroutine
    suspend fun getCurrentWeather() : LiveData<CurrentWeatherEntity>
}