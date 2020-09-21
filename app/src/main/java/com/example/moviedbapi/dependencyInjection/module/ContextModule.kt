package com.example.moviesdbapi.dependencyInjection.module

import android.content.Context
import com.example.moviesdbapi.dependencyInjection.qualifier.ApplicationContext
import com.example.moviesdbapi.dependencyInjection.scope.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class ContextModule(var context: Context) {

    @Provides
    @ApplicationContext
    @ApplicationScope
    fun context():Context
    {
        return context.applicationContext
    }
}