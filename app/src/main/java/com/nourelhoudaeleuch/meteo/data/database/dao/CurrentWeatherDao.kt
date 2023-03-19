package com.nourelhoudaeleuch.meteo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nourelhoudaeleuch.meteo.data.database.entity.CURRENT_ID
import com.nourelhoudaeleuch.meteo.data.database.entity.CurrentWeatherEntity

@Dao
interface CurrentWeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherEntry: CurrentWeatherEntity)


    @Query("select * from current_weather where id = $CURRENT_ID")
    fun getWeather(): LiveData<CurrentWeatherEntity>
}