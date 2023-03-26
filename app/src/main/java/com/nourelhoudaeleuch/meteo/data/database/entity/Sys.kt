package com.nourelhoudaeleuch.meteo.data.database.entity


import com.google.gson.annotations.SerializedName

data class Sys(
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int
)