package com.example.weatherapp.Design

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.weatherapp.ViewModel.WeatherViewModel
import com.example.weatherapp.WeatherApi.WeatherApi


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WeatherUI(weatherViewModel: WeatherViewModel, modifier: Modifier) {
    val city = weatherViewModel.city.collectAsState().value
    val weatherReasult = weatherViewModel.weather.collectAsState().value
    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(
                        Color(40, 46, 90),
                        Color(130, 78, 168)
                    )
                )
            )
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            {
                OutlinedTextField(
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        unfocusedBorderColor = Color.White,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    value = city,
                    onValueChange = {
                        weatherViewModel.setvalue(it)
                    },
                    placeholder = { Text(text = "Search Location", color = Color.White) },
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = {
                    weatherViewModel.getweather(city)
                    keyboardController?.hide()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
            if (weatherReasult != null) {
                weatherDetails(weatherReasult)
            }
        }
    }
}

@Composable
private fun weatherDetails(weatherReasult: WeatherApi?) {
    if (weatherReasult != null) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Location",
                    modifier = Modifier.size(40.dp),
                    tint = Color.White
                )

                Text(
                    text = " ${weatherReasult.location.name}, ${weatherReasult.location.country}",
                    fontSize = 30.sp, color = Color.White
                )

            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "${weatherReasult.current.temp_c}°",
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            AsyncImage(
                modifier = Modifier.size(160.dp),
                model = "https:${weatherReasult.current.condition.icon}".replace(
                    "64x64",
                    "128x128"
                ),
                contentDescription = "Condition icon"
            )
            Text(
                text = weatherReasult.current.condition.text,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                color = Color.White
            )
            Spacer(modifier = Modifier.padding(vertical = 40.dp))
            Column {
                Card(
                    elevation = CardDefaults.elevatedCardElevation(8.dp),
                    modifier = Modifier.padding(10.dp).weight(1f)
                )
                {
                    Column(
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.linearGradient(
                                    colors = listOf(
                                        Color(130, 78, 168),
                                        Color(40, 46, 90)
                                    )
                                )
                            )
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            weatherkeyvalue("Humidity", weatherReasult.current.humidity)
                            weatherkeyvalue("Wind Speed", weatherReasult.current.wind_kph + "km/h")
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            weatherkeyvalue("UV", weatherReasult.current.uv)
                            weatherkeyvalue(
                                "Participation",
                                weatherReasult.current.precip_mm + "mm"
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            weatherkeyvalue(
                                "Local time",
                                weatherReasult.location.localtime.split(" ")[1]
                            )
                            weatherkeyvalue(
                                "Local date",
                                weatherReasult.location.localtime.split(" ")[0]
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.padding(vertical = 10.dp))
            }
        }
    }
}

@Composable
fun weatherkeyvalue(key: String, value: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.White)
        Text(text = key, fontWeight = FontWeight.SemiBold, color = Color.White)
    }

}