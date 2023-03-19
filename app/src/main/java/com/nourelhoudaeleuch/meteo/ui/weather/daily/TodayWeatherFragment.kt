package com.nourelhoudaeleuch.meteo.ui.weather.daily

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nourelhoudaeleuch.meteo.R
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nourelhoudaeleuch.meteo.utils.FragmentScopes


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(TodayWeatherViewModel::class.java)

        bindUI()
    }

    private fun bindUI() = launch {
        val currentWeather = viewModel.weather.await()

        currentWeather.observe(this@TodayWeatherFragment, Observer {})
    }


    }


