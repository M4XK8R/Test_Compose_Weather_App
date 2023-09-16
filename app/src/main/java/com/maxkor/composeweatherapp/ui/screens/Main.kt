package com.maxkor.composeweatherapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.maxkor.composeweatherapp.R
import com.maxkor.composeweatherapp.data.WeatherModelCurrent
import com.maxkor.composeweatherapp.ui.theme.BlueLight80

@Composable
fun MainCard(
    day: MutableState<WeatherModelCurrent>,
    updateData: () -> Unit,
    search: () -> Unit
): String {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(BlueLight80),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = day.value.date,
                    fontSize = 20.sp,
                    color = Color.White
                )
                AsyncImage(
                    model = "https:${day.value.icon}",
                    contentDescription = "Weather Image",
                    modifier = Modifier.size(40.dp)
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = day.value.city,
                    fontSize = 26.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White
                )
                Text(
                    text = day.value.currentTemp + "°C",
                    fontSize = 44.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White,
                    fontWeight = Bold
                )
                Text(
                    text = day.value.condition,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(bottom = 8.dp),
                    color = Color.White
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    IconButton(onClick = {
                        search()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.search),
                            contentDescription = "search image",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    Text(
                        text = day.value.wind + " kph",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(bottom = 8.dp),
                        color = Color.White
                    )
                    IconButton(onClick = {
                        updateData()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.sync),
                            contentDescription = "sync image",
                            tint = Color.White,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                }
            }
        }
    }

    return  day.value.city
}


