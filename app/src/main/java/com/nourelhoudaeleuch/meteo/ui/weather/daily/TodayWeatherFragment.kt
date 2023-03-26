package com.nourelhoudaeleuch.meteo.ui.weather.daily

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nourelhoudaeleuch.meteo.R
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nourelhoudaeleuch.meteo.data.nework.ConnexionInterceptorImpl
import com.nourelhoudaeleuch.meteo.data.nework.WeatherApiService
import com.nourelhoudaeleuch.meteo.data.nework.WeatherNetDataSourceImpl
import com.nourelhoudaeleuch.meteo.utils.FragmentScopes
import kotlinx.android.synthetic.main.fragment_today_weather.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope


class TodayWeatherFragment : FragmentScopes(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModelFactory: TodayWeatherViewModelFactory by instance()
    private lateinit var viewModel: TodayWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TodayWeatherViewModel::class.java)


//        val apiService = WeatherApiService(ConnexionInterceptorImpl(this.context!!))
//        val weatherNetDataSource = WeatherNetDataSourceImpl(apiService)
//
//        weatherNetDataSource.downloadedCurrentWeather.observe(this, Observer {
//            status_textview.text = it.toString()
//            Log.d("TAG", "response : ${it.toString()}")
//        })
//
//        GlobalScope.launch(Dispatchers.Main) {
//            weatherNetDataSource.fetchCurrentWeather("London")
//        }

        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(viewLifecycleOwner, Observer {
            if (it == null) return@Observer
            group_loading.visibility = View.GONE

            updateTemperatures(it.temp,it.tempMin,it.tempMax)
//            updateWind(it.wind.speed)
//            updateVisibility(it.visibility)
            //updateStatus(it.weather)
            //updateCloud(it.clouds.all)
            //updateHumidity(it.humidity)
            updateLocation("Tunis")

        })
    }

    private fun updateTemperatures(temperature: Double, minTemperature: Double,maxTemperature: Double) {

        temperature_textview.text = "$temperature"
        temp_min.text = "$minTemperature"
        temp_max.text = "$maxTemperature"
    }

    private fun updateLocation(location: String) {
        city_textview.text = location
    }

    private fun updateVisibility(visibility: Int) {
        visibility_textview.text = "$visibility"
    }

    private fun updateWind( windSpeed: Double) {
        wind.text = "$windSpeed kmph"
    }

//    private fun updateStatus(status: List<WeatherEntity>) {
//        status_textview.text = status
//    }

    private fun updateCloud(cloud: Int) {
        cloud_textview.text = "$cloud"
    }

    private fun updateHumidity(humidity: Int) {
        humidity_textview.text = "$humidity"
    }


    }


