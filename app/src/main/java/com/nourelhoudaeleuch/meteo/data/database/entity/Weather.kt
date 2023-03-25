package com.nourelhoudaeleuch.meteo.data.database.entity


data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)