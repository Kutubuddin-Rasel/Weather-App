package com.example.weatherapp.Dagger

import com.example.weatherapp.Retrofit.Constant
import com.example.weatherapp.Retrofit.Retrofitapi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit():Retrofit{
        return Retrofit
            .Builder()
            .baseUrl(Constant.Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Singleton
    @Provides
    fun provideWeatherApi(retrofit: Retrofit):Retrofitapi{
        return retrofit.create(Retrofitapi::class.java)
    }
}