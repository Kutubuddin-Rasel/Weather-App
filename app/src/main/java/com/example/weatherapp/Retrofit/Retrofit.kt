package com.example.weatherapp.Retrofit
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    private val Base_Url="https://api.weatherapi.com/"
    fun getInstance():Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(Base_Url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}