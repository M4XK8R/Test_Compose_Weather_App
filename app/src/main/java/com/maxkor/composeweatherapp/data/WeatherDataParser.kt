package com.maxkor.composeweatherapp.data

import com.maxkor.composeweatherapp.createLog
import org.json.JSONObject

class WeatherDataParser {

    fun parseHoursWeather(response: String): List<WeatherModelHour> {
        val json = JSONObject(response)
        val hoursListJson = json.getJSONObject("forecast")
            .getJSONArray("forecastday")
            .getJSONObject(0)
            .getJSONArray("hour")

        val hoursList = ArrayList<WeatherModelHour>()

        for (index in 0 until hoursListJson.length()) {
            val hourJson = hoursListJson.getJSONObject(index)
            val time = hourJson.getString("time")
            val condition = hourJson
                .getJSONObject("condition")
                .getString("text")
            val temp = hourJson.getString("temp_c")
            val icon = hourJson
                .getJSONObject("condition")
                .getString("icon")

            val hour = WeatherModelHour(time, condition, temp, icon)
            hoursList.add(hour)
        }
        return hoursList
    }

    fun parseTodayWeather(response: String): WeatherModelCurrent {
        val json = JSONObject(response)
        val currentInfo = json.getJSONObject("current")

        val date = json
            .getJSONObject("location")
            .getString("localtime")

        val icon = currentInfo
            .getJSONObject("condition")
            .getString("icon")

        val city = json
            .getJSONObject("location")
            .getString("name")

        val temp = currentInfo.getString("temp_c")

        val condition = currentInfo
            .getJSONObject("condition")
            .getString("text")

        val wind = currentInfo.getString("wind_kph")

        return WeatherModelCurrent(date, icon, city, temp, condition, wind)
    }


    fun parse3DaysWeather(response: String): List<WeatherModelDay> {
        if (response.isEmpty()) return listOf()

        val list = ArrayList<WeatherModelDay>()
        val json = JSONObject(response)

        val daysList = json
            .getJSONObject("forecast")
            .getJSONArray("forecastday")

        for (position in 0 until daysList.length()) {
            val day = daysList.getJSONObject(position)
            val date = day.getString("date")
            val dayInfo = day.getJSONObject("day")

            val condition = dayInfo
                .getJSONObject("condition")
                .getString("text")

            val icon = dayInfo
                .getJSONObject("condition")
                .getString("icon")

            val minTemp = dayInfo.getString("mintemp_c")
            val maxTemp = dayInfo.getString("maxtemp_c")

            list.add(
                WeatherModelDay(date, condition, icon, minTemp, maxTemp)
            )
        }
        return list
    }

}


























