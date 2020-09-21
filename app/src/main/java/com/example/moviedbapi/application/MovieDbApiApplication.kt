package com.example.moviesdbapi.application

import android.app.Application

class MovieDbApiApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        mApplicationContext=this
    }

    companion object{
        lateinit var mApplicationContext : Application
    }
}