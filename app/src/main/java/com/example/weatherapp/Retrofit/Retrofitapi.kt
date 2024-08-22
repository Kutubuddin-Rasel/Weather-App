package com.example.weatherapp.Retrofit

import com.example.weatherapp.WeatherApi.WeatherApi
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Retrofitapi {
    @GET("v1/current.json")
    suspend fun getWeather(@Query("key") apikey:String,@Query("q") city:String):Response<WeatherApi>
}