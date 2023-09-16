package com.maxkor.composeweatherapp.data

data class WeatherModelCurrent(
    val date: String,
    val icon: String,
    val city: String,
    val currentTemp: String,
    val condition: String,
    val wind: String,
)
