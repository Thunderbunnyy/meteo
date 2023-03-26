package com.nourelhoudaeleuch.meteo.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nourelhoudaeleuch.meteo.data.database.dao.*
import com.nourelhoudaeleuch.meteo.data.database.entity.*

@Database(
    entities = [Main::class, Clouds::class,Weather::class, Wind::class, Coord::class],
    version = 3,
    exportSchema = false
)

abstract class WeatherDatabase: RoomDatabase() {
    abstract fun temperatureDao(): MainDao
    abstract fun cloudsDao(): CloudDao
    abstract fun weatherDao(): WeatherDao
    abstract fun windDow(): WindDao
    abstract fun locationDao(): LocationDao

    //we need a singleton
    companion object{
        @Volatile private var instance: WeatherDatabase? = null
        //make sure one thread only is working
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            //check if the instance is initialized
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                WeatherDatabase::class.java, "weather.db")
                .fallbackToDestructiveMigration()
                .build()

    }

}