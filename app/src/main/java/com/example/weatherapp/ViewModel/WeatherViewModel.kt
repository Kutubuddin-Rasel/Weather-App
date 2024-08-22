package com.example.weatherapp.ViewModel

import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.Retrofit.API
import com.example.weatherapp.WeatherApi.NetworkResponse
import com.example.weatherapp.WeatherApi.WeatherApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(private val  weatherRepository: WeatherRepository):ViewModel() {
    private val _city = MutableStateFlow("")
    val city = _city
    fun setvalue(city:String){
        _city.value=city
    }
    val weather = weatherRepository.weather
    fun getweather(city:String) {
        viewModelScope.launch(Dispatchers.IO) {
           val data =weatherRepository.getWeather(API.api,city)
        }
    }
}