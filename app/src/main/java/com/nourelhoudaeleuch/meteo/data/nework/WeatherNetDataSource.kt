package com.nourelhoudaeleuch.meteo.data.nework

import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.database.entity.Main
import com.nourelhoudaeleuch.meteo.data.responses.CurrentWeatherByCityResponse

interface WeatherNetDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherByCityResponse>

    suspend fun fetchWeather(location: String)

}