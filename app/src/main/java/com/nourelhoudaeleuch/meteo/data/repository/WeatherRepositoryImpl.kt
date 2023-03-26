package com.nourelhoudaeleuch.meteo.data.repository

import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.database.dao.CurrentWeatherDao
import com.nourelhoudaeleuch.meteo.data.database.entity.Main
import com.nourelhoudaeleuch.meteo.data.nework.WeatherNetDataSource
import com.nourelhoudaeleuch.meteo.data.responses.CurrentWeatherByCityResponse
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime

class WeatherRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetDataSource: WeatherNetDataSource
    ) : WeatherRepository {

    init {
        weatherNetDataSource.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather:CurrentWeatherByCityResponse ->
                //persist data
                persistFetchedCurrentWeather(newCurrentWeather)
            }
    }
    }

    override suspend fun getCurrentWeather(): LiveData<out Main> {
        initWeatherData()
        return withContext(Dispatchers.IO){
            return@withContext currentWeatherDao.getWeather()
        }

    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherByCityResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.main)
        }
    }

    private suspend fun initWeatherData() {
        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetDataSource.fetchCurrentWeather("Tunis")
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val fifteenMinutesAgo = ZonedDateTime.now().minusMinutes(15)
        return lastFetchTime.isBefore(fifteenMinutesAgo)
    }

}