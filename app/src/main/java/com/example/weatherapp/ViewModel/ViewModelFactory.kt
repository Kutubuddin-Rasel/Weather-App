package com.example.weatherapp.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Repository.WeatherRepository
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val weatherRepository: WeatherRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return WeatherViewModel(weatherRepository) as T
    }
}