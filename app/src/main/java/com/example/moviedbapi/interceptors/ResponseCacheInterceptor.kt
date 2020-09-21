package com.example.moviesdbapi.interceptors

import com.example.moviesdbapi.dependencyInjection.qualifier.ResponseInterceptor
import com.example.moviesdbapi.utilities.Constant
import dagger.Module
import dagger.Provides
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Response
import java.util.concurrent.TimeUnit

@Module
class ResponseCacheInterceptor : Interceptor {

    @Provides
    override fun intercept(chain: Interceptor.Chain): Response {
        val response=chain.proceed(chain.request())
        val cacheControl=CacheControl.Builder().maxAge(1, TimeUnit.MINUTES).build()
        return response.newBuilder()
            .removeHeader("pragma")
            .addHeader(Constant.CACHE_HEADER,cacheControl.toString()).build()
    }

}