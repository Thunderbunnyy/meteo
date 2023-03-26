package com.nourelhoudaeleuch.meteo.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nourelhoudaeleuch.meteo.data.database.entity.CLOUDS_ID
import com.nourelhoudaeleuch.meteo.data.database.entity.Clouds



@Dao
interface CloudDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(weatherClouds: Clouds)

    @Query("select * from clouds where id = $CLOUDS_ID")
    fun getClouds(): LiveData<Clouds>

}