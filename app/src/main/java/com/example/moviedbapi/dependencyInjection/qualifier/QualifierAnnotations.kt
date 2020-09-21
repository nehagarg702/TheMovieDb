package com.example.moviesdbapi.dependencyInjection.qualifier

import javax.inject.Qualifier

@Qualifier
annotation class LoggerInterceptor

@Qualifier
annotation class ResponseInterceptor

@Qualifier
annotation class OfflineResponseInterceptor

@Qualifier
annotation class ApplicationContext

@Qualifier
annotation class ActivityContext

@Qualifier
annotation class NetworkAvailable
