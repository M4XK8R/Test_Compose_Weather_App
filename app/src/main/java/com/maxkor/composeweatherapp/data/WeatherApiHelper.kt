package com.maxkor.composeweatherapp.data

import android.content.Context
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.maxkor.composeweatherapp.createLog

private const val API_KEY = "1b10194b87f44f03a66111716230309"
private const val BASE_URL = "https://api.weatherapi.com/v1/"
private const val DAYS_AMOUNT = 3

class WeatherApiHelper {

    fun getWeatherToday(
        context: Context,
        city: String,
        day: MutableState<WeatherModelCurrent>
    ) {
        val url = BASE_URL + "current.json?" +
                "key=$API_KEY&" +
                "q=$city&" +
                "aqi=no"

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val weatherDataParser = WeatherDataParser()
                val responseDay =
                    weatherDataParser.parseTodayWeather(response)
                day.value = responseDay
            },
            { error ->
                createLog("Error: $error")
            }
        )
        queue.add(stringRequest)

    }

    fun getWeatherFor3Days(
        context: Context,
        city: String,
        daysList: MutableState<List<WeatherModelDay>>,
        hoursList: MutableState<List<WeatherModelHour>>
    ) {
        val url = BASE_URL + "forecast.json?" +
                "key=$API_KEY&" +
                "q=$city&" +
                "days=$DAYS_AMOUNT&" +
                "aqi=no&" +
                "alerts=no"

        val queue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            url,
            { response ->
                val weatherDataParser = WeatherDataParser()
                val responseListDays =
                        weatherDataParser.parse3DaysWeather(response)
                daysList.value = responseListDays

                val responseListHours =
                    weatherDataParser.parseHoursWeather(response)
                hoursList.value = responseListHours
            },
            { error ->
                createLog(error.toString())
            }
        )
        queue.add(stringRequest)
    }

}








