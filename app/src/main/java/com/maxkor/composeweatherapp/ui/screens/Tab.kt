package com.maxkor.composeweatherapp.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maxkor.composeweatherapp.data.WeatherModelDay
import com.maxkor.composeweatherapp.data.WeatherModelHour
import com.maxkor.composeweatherapp.ui.theme.BlueLight65
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabLayout(
    daysList: MutableState<List<WeatherModelDay>>,
    hoursList: MutableState<List<WeatherModelHour>>
) {
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val tabList = listOf("Days", "Hours")

    Column(
        modifier = Modifier
            .padding(start = 8.dp, end = 8.dp)
            .clip(RoundedCornerShape(25.dp))
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            contentColor = Color.White,
            containerColor = BlueLight65,
        ) {
            tabList.forEachIndexed { index, title ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    }
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(
                            horizontal = 16.dp,
                            vertical = 8.dp
                        ),
                        fontSize = 22.sp
                    )
                }
            }
        }
        HorizontalPager(
            pageCount = tabList.size,
            state = pagerState,
            modifier = Modifier.weight(1.0f)
        ) { page ->
            LazyColumn() {
                when (page) {
                    0 -> {
                        itemsIndexed(
                            daysList.value
                        ) { _, item ->
                            ListDays(day = item)
                        }
                    }

                    1 -> {
                        itemsIndexed(
                            hoursList.value
                        ) { _, item ->
                            ListHours(hour = item)
                        }
                    }
                }
            }
        }
    }
}