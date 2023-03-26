package com.nourelhoudaeleuch.meteo.data.nework

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nourelhoudaeleuch.meteo.data.responses.CurrentWeatherByCityResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="60465f0acfb15ee7ee182519e7f67c29"

//https://api.openweathermap.org/data/2.5/weather?q=London&appid={API key}

interface WeatherApiService {

    @GET("weather")
    fun getWeather(
        @Query("q") city: String,
        @Query("units") metric: String="metric"
    //return a deferred response because the network call might take some time, so we await
    // for it
    ) : Deferred<CurrentWeatherByCityResponse>

    //fetching data
    companion object {
        operator fun invoke(
           connexionInterceptor: ConnexionInterceptor
        ): WeatherApiService {
            val requestInterceptor = Interceptor { chain ->
                val url= chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter("appid", API_KEY)
                    .build()
                val request = chain.request()
                    .newBuilder()
                    .url(url)
                    .build()

                return@Interceptor chain.proceed(request)
            }

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connexionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()) //use Gson to parse json
                .build()
                .create(WeatherApiService::class.java)
        }
    }

}