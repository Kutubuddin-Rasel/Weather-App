package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.Dagger.ApplicationComponent
import com.example.weatherapp.Dagger.DaggerApplicationComponent

class WeatherApplication:Application() {
    lateinit var applicationComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder().build()
    }
}