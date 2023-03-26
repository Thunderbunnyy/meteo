package com.nourelhoudaeleuch.meteo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nourelhoudaeleuch.meteo.data.database.entity.Main
import com.nourelhoudaeleuch.meteo.data.database.entity.TEMP_ID
import com.nourelhoudaeleuch.meteo.data.database.entity.WIND_ID
import com.nourelhoudaeleuch.meteo.data.database.entity.Wind

@Dao
interface WindDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherWind: Wind)

    @Query("select * from wind where id = $WIND_ID")
    fun getWind(): LiveData<Wind>

}