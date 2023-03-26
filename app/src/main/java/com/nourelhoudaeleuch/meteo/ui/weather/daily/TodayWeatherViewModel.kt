package com.nourelhoudaeleuch.meteo.ui.weather.daily

import androidx.lifecycle.ViewModel
import com.nourelhoudaeleuch.meteo.data.repository.WeatherRepository
import com.nourelhoudaeleuch.meteo.utils.lazyDeferred

class TodayWeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {

    //don't instantiate instantly, wait for call
    val temp by lazyDeferred {
        weatherRepository.getTemperature()

    }

    val wind by lazyDeferred {
        weatherRepository.getWind()
    }

    val cloud by lazyDeferred {
        weatherRepository.getClouds()
    }

    val weather by lazyDeferred {
        weatherRepository.getWeather()
    }
}