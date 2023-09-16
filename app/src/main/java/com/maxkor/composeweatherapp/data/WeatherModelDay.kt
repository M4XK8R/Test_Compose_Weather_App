package com.maxkor.composeweatherapp.data

data class WeatherModelDay(
    val date: String,
    val condition: String,
    val icon: String,
    val minTemp: String,
    val maxTemp: String,
)
