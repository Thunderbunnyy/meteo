package com.nourelhoudaeleuch.meteo

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nourelhoudaeleuch.meteo.data.repository.WeatherRepository
import com.nourelhoudaeleuch.meteo.data.repository.WeatherRepositoryImpl
import com.nourelhoudaeleuch.meteo.data.database.WeatherDatabase
import com.nourelhoudaeleuch.meteo.data.nework.*
import com.nourelhoudaeleuch.meteo.ui.weather.daily.TodayWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*

class MeteoApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MeteoApp))

        bind() from singleton { WeatherDatabase(instance()) }
        bind() from singleton { instance<WeatherDatabase>().currentWeatherDao() }

        bind() from singleton { WeatherApiService(instance()) }

        bind<WeatherNetDataSource>() with singleton { WeatherNetDataSourceImpl(instance()) }
        bind<ConnexionInterceptor>() with singleton { ConnexionInterceptorImpl(instance()) }
        bind<WeatherRepository>() with singleton { WeatherRepositoryImpl(instance(), instance()) }

        bind() from provider { TodayWeatherViewModelFactory(instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }

}