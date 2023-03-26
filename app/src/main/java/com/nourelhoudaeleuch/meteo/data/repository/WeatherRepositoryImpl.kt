package com.nourelhoudaeleuch.meteo.data.repository

import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.database.dao.*
import com.nourelhoudaeleuch.meteo.data.database.entity.*
import com.nourelhoudaeleuch.meteo.data.nework.WeatherNetDataSource
import com.nourelhoudaeleuch.meteo.data.responses.CurrentWeatherByCityResponse
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime

class WeatherRepositoryImpl(
    private val mainDao: MainDao,
    private val weatherDao: WeatherDao,
    private val cloudDao: CloudDao,
    private val windDao: WindDao,
    private val locationDao: LocationDao,
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

    override suspend fun getTemperature(): LiveData<out Main> {
        initWeatherData()
        return withContext(Dispatchers.IO){
            return@withContext mainDao.getTemperature()
        }

    }

    override suspend fun getClouds(): LiveData<out Clouds> {
        return withContext(Dispatchers.IO){
            return@withContext cloudDao.getClouds()
        }
    }

    override suspend fun getWind(): LiveData<out Wind> {
        return withContext(Dispatchers.IO){
            return@withContext windDao.getWind()
        }
    }

    override suspend fun getWeather(): LiveData<out Weather> {
        return withContext(Dispatchers.IO){
            return@withContext weatherDao.getWeather()
        }
    }

    override suspend fun getLocation(): LiveData<out Coord> {
        return withContext(Dispatchers.IO){
            return@withContext locationDao.getLocation()
        }
    }

    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherByCityResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            mainDao.upsert(fetchedWeather.main)
            weatherDao.upsert(fetchedWeather.weather)
            cloudDao.upsert(fetchedWeather.clouds)
            windDao.upsert(fetchedWeather.wind)
            locationDao.upsert(fetchedWeather.coord)

            fetchedWeather.visibility
            fetchedWeather.name
        }
    }

    private suspend fun initWeatherData() {

        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchWeather()
    }

    private suspend fun fetchWeather() {
        weatherNetDataSource.fetchWeather("Tunis")
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val fifteenMinutesAgo = ZonedDateTime.now().minusMinutes(15)
        return lastFetchTime.isBefore(fifteenMinutesAgo)
    }

}