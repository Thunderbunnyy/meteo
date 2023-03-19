package com.nourelhoudaeleuch.meteo.data.network

import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.network.response.CurrentWeatherResponse

interface WeatherNetDataSource {

    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    suspend fun fetchCurrentWeather(location: String)

}