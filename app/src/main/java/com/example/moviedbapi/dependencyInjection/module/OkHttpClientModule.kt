package com.example.moviesdbapi.dependencyInjection.module

import android.content.Context
import com.example.moviesdbapi.utilities.Constant
import com.example.moviesdbapi.dependencyInjection.qualifier.ApplicationContext
import com.example.moviesdbapi.dependencyInjection.qualifier.LoggerInterceptor
import com.example.moviesdbapi.dependencyInjection.qualifier.OfflineResponseInterceptor
import com.example.moviesdbapi.dependencyInjection.qualifier.ResponseInterceptor
import com.example.moviesdbapi.dependencyInjection.scope.ApplicationScope
import com.example.moviesdbapi.interceptors.OfflineResponseCacheInterceptor
import com.example.moviesdbapi.interceptors.ResponseCacheInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.io.File
import java.util.concurrent.TimeUnit

@Module(includes = [ResponseCacheInterceptor::class, OfflineResponseCacheInterceptor::class])
class OkHttpClientModule {

    @Provides
    @LoggerInterceptor
    fun getLoggerInterceptor():Interceptor {
        val logger= HttpLoggingInterceptor()
        logger.level=HttpLoggingInterceptor.Level.BODY
        return logger;
    }

    @Provides
    @ApplicationScope
    fun getFile(@ApplicationContext context: Context) : File{
        return File(context.cacheDir, Constant.CACHE_FILE_NAME)
    }

    @Provides
    fun getCache(file: File):Cache {
        return Cache(file,10*2048*2048)
    }

    @Provides
    @ApplicationScope
    fun getOkHttpClient(@LoggerInterceptor logger : Interceptor, @OfflineResponseInterceptor offlineResponse :Interceptor, cache: Cache,
                        @ResponseInterceptor response :Interceptor) : OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor(offlineResponse)
            .addNetworkInterceptor(response)
            .cache(cache)
            .readTimeout(1,TimeUnit.MINUTES)
            .connectTimeout(1,TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @ResponseInterceptor
    fun getNetworkResponseInterceptor():Interceptor {
        return ResponseCacheInterceptor()
    }

    @Provides
    @OfflineResponseInterceptor
    fun getOfflineCacheResponseInterceptor():Interceptor {
        return OfflineResponseCacheInterceptor()
    }

}