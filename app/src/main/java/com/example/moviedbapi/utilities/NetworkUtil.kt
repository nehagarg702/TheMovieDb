package com.example.moviesdbapi.utilities

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.moviesdbapi.application.MovieDbApiApplication
import com.example.moviesdbapi.dependencyInjection.module.ContextModule
import com.example.moviesdbapi.dependencyInjection.qualifier.ApplicationContext
import com.example.moviesdbapi.dependencyInjection.qualifier.NetworkAvailable
import dagger.Module
import dagger.Provides

@Module(includes = [ContextModule::class])
class NetworkUtil {

  @Provides
  @NetworkAvailable
  fun getNetworkAvailable(@ApplicationContext context: Context): Boolean
  {
      val connectivityManager : ConnectivityManager=MovieDbApiApplication.mApplicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
          val nw      = connectivityManager.activeNetwork ?: return false
          val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
          return when {
              actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
              actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)|| actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
              else -> false
          }
      } else {
          return connectivityManager.activeNetworkInfo!=null && connectivityManager.activeNetworkInfo!!.isConnectedOrConnecting
      }
  }

}