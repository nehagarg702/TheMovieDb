package com.example.moviesdbapi.interceptors

import com.example.moviesdbapi.application.MovieDbApiApplication
import com.example.moviesdbapi.dependencyInjection.qualifier.NetworkAvailable
import com.example.moviesdbapi.utilities.Constant
import com.example.moviesdbapi.utilities.NetworkUtil
import dagger.Module
import dagger.Provides
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@Module(includes = [NetworkUtil::class])
class OfflineResponseCacheInterceptor :Interceptor {

    @Provides
    override fun intercept(chain: Interceptor.Chain): Response {
        var response=chain.request()
        if(!NetworkUtil().getNetworkAvailable(MovieDbApiApplication.mApplicationContext))
        {
            val cacheControl=CacheControl.Builder().maxAge(10,TimeUnit.DAYS).build()
            response=response.newBuilder()
                .removeHeader("pragma")
                .addHeader(Constant.CACHE_HEADER, cacheControl.toString()).build()
        }
        return chain.proceed(response)
    }

}