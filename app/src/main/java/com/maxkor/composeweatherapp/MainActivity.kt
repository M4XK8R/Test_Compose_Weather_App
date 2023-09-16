package com.maxkor.composeweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.maxkor.composeweatherapp.data.WeatherApiHelper
import com.maxkor.composeweatherapp.data.WeatherModelCurrent
import com.maxkor.composeweatherapp.data.WeatherModelDay
import com.maxkor.composeweatherapp.data.WeatherModelHour
import com.maxkor.composeweatherapp.ui.screens.MainCard
import com.maxkor.composeweatherapp.ui.screens.TabLayout
import com.maxkor.composeweatherapp.ui.theme.ComposeWeatherAppTheme

class MainActivity : ComponentActivity() {
    private val weatherApiHelper = WeatherApiHelper()
    private lateinit var day: MutableState<WeatherModelCurrent>
    private lateinit var city: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isNeedToUpdateMainCardData = true

        val blankModel = WeatherModelCurrent(
            "",
            "",
            "",
            "",
            "",
            ""
        )

        setContent {
            createLog("setContent")
            ComposeWeatherAppTheme {
                createLog("ComposeWeatherAppTheme")
                val dialogState = remember {
                    mutableStateOf(false)
                }
                if (dialogState.value) {
                    ShowDialog(dialogState)
                }

                val cityState = remember {
                    mutableStateOf(String())
                }

                val daysList = remember {
                    mutableStateOf(listOf<WeatherModelDay>())
                }
                val hourList = remember {
                    mutableStateOf(listOf<WeatherModelHour>())
                }
                weatherApiHelper.getWeatherFor3Days(
                    this,
                    "London",
                    daysList,
                    hourList
                )

                day = remember {
                    mutableStateOf(blankModel)
                }
                if (isNeedToUpdateMainCardData) {
                    weatherApiHelper.getWeatherToday(
                        this,
                        "London",
                        day
                    )
                    isNeedToUpdateMainCardData = false
                }

                Image(
                    painter = painterResource(id = R.drawable.sky),
                    contentDescription = "Sky Image",
                    modifier = Modifier
                        .fillMaxSize()
                        .alpha(0.5f),
                    contentScale = ContentScale.Crop
                )
                Column {
                    city = MainCard(
                        day,
                        {
                            weatherApiHelper.getWeatherToday(
                                this@MainActivity,
                                city,
                                day
                            )
                            createLog("city = $city")
                        },
                        {
                            dialogState.value = true
                        }
                    )
                    TabLayout(daysList, hourList)
                }
            }
        }
    }

    @Composable
    fun ShowDialog(dialogState: MutableState<Boolean>) {
        var dialogText by remember {
            mutableStateOf("")
        }

        AlertDialog(
            onDismissRequest = {},
            confirmButton = {
                TextButton(onClick = {
                    weatherApiHelper.getWeatherToday(
                        this@MainActivity,
                        dialogText,
                        day
                    )
                    dialogState.value = false
                }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogState.value = false
                }
                ) {
                    Text(text = "Cancel")
                }
            },
            title = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Input city name")
                    TextField(value = dialogText, onValueChange = {
                        dialogText = it
                    })
                }
            }
        )
    }

}


