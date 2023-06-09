package com.nourelhoudaeleuch.meteo.data.responses


import com.nourelhoudaeleuch.meteo.data.database.entity.*

data class CurrentWeatherByCityResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val id: Int,
    val name: String,
    val cod: Int
)