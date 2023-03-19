package com.nourelhoudaeleuch.meteo.ui.weather.weekly

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nourelhoudaeleuch.meteo.R

class WeekWeatherFragment : Fragment() {

    companion object {
        fun newInstance() = WeekWeatherFragment()
    }

    private lateinit var viewModel: WeekWeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_week_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}