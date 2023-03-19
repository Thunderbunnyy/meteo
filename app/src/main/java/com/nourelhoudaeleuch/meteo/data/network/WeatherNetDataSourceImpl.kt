package com.nourelhoudaeleuch.meteo.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nourelhoudaeleuch.meteo.data.network.response.CurrentWeatherResponse
import com.nourelhoudaeleuch.meteo.utils.noInternetException

class WeatherNetDataSourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetDataSource {

    private val mDownloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>

    get() = mDownloadedCurrentWeather

    override suspend fun fetchCurrentWeather(location: String) {
        try {
            val fetchedCurrentWeather = weatherApiService
                .getCurrentWeatherByCityAsync(location)
                .await()

            mDownloadedCurrentWeather.postValue(fetchedCurrentWeather)
        }catch (e: noInternetException){
            Log.e("Connexion", "No internet.", e)
        }
    }
}