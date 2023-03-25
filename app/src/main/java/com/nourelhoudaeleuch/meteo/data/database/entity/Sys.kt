package com.nourelhoudaeleuch.meteo.data.database.entity


data class Sys(
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)