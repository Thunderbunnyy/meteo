package com.nourelhoudaeleuch.meteo.data.nework

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nourelhoudaeleuch.meteo.data.responses.CurrentWeatherByCityResponse
import com.nourelhoudaeleuch.meteo.utils.noInternetException

class WeatherNetDataSourceImpl(
    private val weatherApiService: WeatherApiService
) : WeatherNetDataSource {

    private val mDownloadedCurrentWeather = MutableLiveData<CurrentWeatherByCityResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherByCityResponse>

    get() = mDownloadedCurrentWeather

    override suspend fun fetchWeather(location: String) {
        try {
            val fetchedWeather = weatherApiService
                .getWeather(location)
                .await()
            mDownloadedCurrentWeather.postValue(fetchedWeather)

        }catch (e: noInternetException){
            Log.e("Connexion", "No internet.", e)
        }
    }
}