package com.nourelhoudaeleuch.meteo.ui.weather.daily

import androidx.lifecycle.ViewModel
import com.nourelhoudaeleuch.meteo.data.repository.WeatherRepository
import com.nourelhoudaeleuch.meteo.utils.lazyDeferred

class TodayWeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    //getCurrentWeather() needs a coroutine context
    val weather by lazyDeferred {
        weatherRepository.getCurrentWeather()

    }

}