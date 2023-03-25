package com.nourelhoudaeleuch.meteo.data.nework

import android.content.Context
import android.net.ConnectivityManager
import com.nourelhoudaeleuch.meteo.utils.noInternetException
import okhttp3.Interceptor
import okhttp3.Response

class ConnexionInterceptorImpl(
    context: Context
) : ConnexionInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isNetworkAvailable())
            throw noInternetException()
        return chain.proceed(chain.request())
    }

//    fun isNetworkAvailable(context: Context) =
//        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).run {
//            getNetworkCapabilities(activeNetwork)?.run {
//                hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)
//                        || hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
//                        || hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
//            } ?: false
//        }

    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo !=null && networkInfo.isConnected

    }

}