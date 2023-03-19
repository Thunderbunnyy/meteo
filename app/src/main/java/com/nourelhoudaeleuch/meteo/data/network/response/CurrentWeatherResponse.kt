package com.nourelhoudaeleuch.meteo.data.network.response

import com.google.gson.annotations.SerializedName
import com.nourelhoudaeleuch.meteo.data.database.entity.CurrentWeatherEntity

data class CurrentWeatherResponse(
    @SerializedName("current")
    val currentWeatherEntity: CurrentWeatherEntity
)