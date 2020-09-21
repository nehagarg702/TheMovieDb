package com.example.moviesdbapi.dependencyInjection.module

import com.example.moviesdbapi.dependencyInjection.scope.ApplicationScope
import com.example.moviesdbapi.utilities.Constant
import com.example.moviesdbapi.repository.MovieDbApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [OkHttpClientModule::class])
class RetrofitModule {

    @Provides
    fun getRetrofitApiService(retrofit: Retrofit):MovieDbApiService {
        return retrofit.create(MovieDbApiService::class.java)
    }

    @Provides
    @ApplicationScope
    fun getRetrofit(okHttpClient: OkHttpClient):Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getGsonConverterFactory(gson:Gson) : GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun getGson() : Gson {
        return GsonBuilder().create()
    }
}