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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.maxkor.composeweatherapp.data.WeatherModelDay
import com.maxkor.composeweatherapp.ui.theme.BlueLight50

@Composable
fun ListDays(day: WeatherModelDay) {
    Card(
        modifier = Modifier
            .padding(top = 5.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(BlueLight50)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = day.date,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(5.dp)
                )
                Text(
                    text = day.condition,
                    fontSize = 20.sp,
                    color = Color.White,
                    modifier = Modifier.padding(start = 5.dp, bottom = 5.dp)
                )
            }
            Text(
                text = day.minTemp + "/" + day.maxTemp + "°C",
                fontSize = 24.sp,
                color = Color.White
            )
            AsyncImage(
                model = "https:${day.icon}",
                contentDescription = "Im",
                modifier = Modifier.size(40.dp)
            )
        }
    }
}