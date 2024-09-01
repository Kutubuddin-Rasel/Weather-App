package com.example.weatherapp.Repository

import com.example.weatherapp.Retrofit.Retrofitapi
import com.example.weatherapp.WeatherApi.NetworkResponse
import com.example.weatherapp.WeatherApi.WeatherApi
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val retrofitapi: Retrofitapi) {
    private val _weather = MutableStateFlow<WeatherApi?>(null)
    val weather = _weather
    suspend fun getWeather(apikey: String, city: String) {
        val result = retrofitapi.getWeather(apikey, city)
        if (result?.body()!=null) {
                weather.emit(result.body())
        }
    }
}