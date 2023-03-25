package com.nourelhoudaeleuch.meteo.ui.weather.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nourelhoudaeleuch.meteo.data.repository.WeatherRepository

class TodayWeatherViewModelFactory(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TodayWeatherViewModel(weatherRepository) as T
    }
}