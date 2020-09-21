package com.example.moviesdbapi.dependencyInjection.component

import com.example.moviesdbapi.dependencyInjection.module.RetrofitModule
import com.example.moviesdbapi.dependencyInjection.scope.ApplicationScope
import com.example.moviesdbapi.repository.MovieDbApiService
import dagger.Component

@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface MovieDbComponent {
    fun getMovieDbService() : MovieDbApiService
}