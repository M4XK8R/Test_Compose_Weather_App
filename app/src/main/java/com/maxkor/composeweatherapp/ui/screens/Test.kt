package com.maxkor.composeweatherapp.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.maxkor.composeweatherapp.createLog
import com.maxkor.composeweatherapp.data.WeatherApiHelper

@Composable
fun ShowTemperature(cityName: String, context: Context) {
    val weatherApiHelper = WeatherApiHelper()

    val temperatureState = remember {
        mutableStateOf("Unknown")
    }

    Column(Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Temp in $cityName = ${temperatureState.value}", fontSize = 20.sp)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.BottomCenter
        ) {
            Button(onClick = {
//                weatherApiHelper.getWeatherToday(context, cityName,)
//                    temperatureState)
                createLog("Button onClick ")
            }
            ) {
                Text(text = "Update", fontSize = 22.sp)
            }
        }
    }
}
