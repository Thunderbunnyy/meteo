package com.nourelhoudaeleuch.meteo.data.network

import com.nourelhoudaeleuch.meteo.data.database.entity.CurrentWeatherEntity
import com.nourelhoudaeleuch.meteo.data.network.ConnexionInterceptor
import com.nourelhoudaeleuch.meteo.data.network.response.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val API_KEY="b2b7cd9db84fb6e2ee9e404c9ad39216"

interface WeatherApiService {

    @GET("forecast")
    fun getCurrentWeatherByCityAsync(
        @Query("q") city: String
    ) : Deferred<CurrentWeatherResponse>

    companion object {
        operator fun invoke(
            connexionInterceptor: ConnexionInterceptor
        ): WeatherApiService {
                val requestInterceptor = Interceptor{chain ->
                     val url = chain.request()
                        .url()
                        .newBuilder()
                        .addQueryParameter("key",API_KEY)
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
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
                }
        }

    }

