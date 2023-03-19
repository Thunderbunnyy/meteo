package com.nourelhoudaeleuch.meteo.data.repository


import androidx.lifecycle.LiveData
import com.nourelhoudaeleuch.meteo.data.database.dao.CurrentWeatherDao
import com.nourelhoudaeleuch.meteo.data.database.entity.CurrentWeatherEntity
import com.nourelhoudaeleuch.meteo.data.network.WeatherNetDataSource
import com.nourelhoudaeleuch.meteo.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.*
import org.threeten.bp.ZonedDateTime

class WeatherRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val weatherNetDataSource: WeatherNetDataSource,

) : WeatherRepository {

    init {
        weatherNetDataSource.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                persistFetchedCurrentWeather(newCurrentWeather)
            }
    }
    }

    override suspend fun getCurrentWeather(): LiveData<CurrentWeatherEntity> {
        initWeatherData()
        return withContext(Dispatchers.IO) {
            return@withContext currentWeatherDao.getWeather()
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.upsert(fetchedWeather.currentWeatherEntity)
        }
    }

    private suspend fun initWeatherData() {
//        val lastWeatherLocation = weatherLocationDao.getLocationNonLive()
//
//        if (lastWeatherLocation == null
//            || locationProvider.hasLocationChanged(lastWeatherLocation)) {
//            fetchCurrentWeather()
//            fetchFutureWeather()
//            return
//        }

        if (isFetchCurrentNeeded(ZonedDateTime.now().minusHours(1)))
            fetchCurrentWeather()

//        if (isFetchFutureNeeded())
//            fetchFutureWeather()
    }

    private suspend fun fetchCurrentWeather() {
        weatherNetDataSource.fetchCurrentWeather("Tunis")
    }

    private fun isFetchCurrentNeeded(lastFetchTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchTime.isBefore(thirtyMinutesAgo)
    }

}