package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.Design.WeatherUI
import com.example.weatherapp.Repository.WeatherRepository
import com.example.weatherapp.Retrofit.Retrofitapi
import com.example.weatherapp.ViewModel.ViewModelFactory
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.WeatherApi.WeatherApi
import com.example.weatherapp.ui.theme.WeatherAppTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        (application as WeatherApplication).applicationComponent.inject(this)
        val viewmodel = ViewModelProvider(this,viewModelFactory).get(WeatherViewModel::class.java)
        setContent {
            WeatherAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    WeatherUI(weatherViewModel = viewmodel, modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
