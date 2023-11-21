package com.gmail.redballtoy.sdweatherone.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.gmail.redballtoy.sdweatherone.R
import com.gmail.redballtoy.sdweatherone.data.WeatherModel
import com.gmail.redballtoy.sdweatherone.data.getWeatherByHours
import com.gmail.redballtoy.sdweatherone.ui.theme.BlueLight
import com.gmail.redballtoy.sdweatherone.utils.convertTempToInt
import com.gmail.redballtoy.sdweatherone.utils.getWeatherIcon
import kotlinx.coroutines.launch

const val ALPHA_SCREEN = 0.6f
const val ROUNDED_CORNER = 10
const val API_KEY = "987b9b98929e4e3aa3a74358221905"

@Preview(showBackground = true)
@Composable
fun MainCardComp(
    currentDay: MutableState<WeatherModel>,
    onClickSync:()-> Unit,
    onClickSearch:()-> Unit,
) {
    Column(
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .alpha(ALPHA_SCREEN),
            colors = CardDefaults.cardColors(BlueLight),
            elevation = CardDefaults.cardElevation(4.dp),
            shape = RoundedCornerShape(ROUNDED_CORNER.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 4.dp, top = 8.dp, end = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = currentDay.value.lastUpdatedTime,
                        fontSize = TextUnit(16f, TextUnitType.Sp),
                        color = Color.White,
                    )
                    Image(
                        modifier = Modifier.size(56.dp),
                        painter = painterResource(getWeatherIcon(currentDay.value.weatherCondition)),
                        contentDescription = currentDay.value.weatherCondition
                    )

                }
                Text(
                    text = "${currentDay.value.city} (${currentDay.value.region})",
                    fontSize = TextUnit(36f, TextUnitType.Sp),
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "${convertTempToInt(currentDay.value.currentTemp)}°C " +
                            "как ${convertTempToInt(currentDay.value.feelsLikeTemp)}°С",
                    fontSize = TextUnit(32f, TextUnitType.Sp),
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = currentDay.value.weatherCondition,
                    fontSize = TextUnit(24f, TextUnitType.Sp),
                    color = Color.White,
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        onClickSearch.invoke()
                    }) {
                        Image(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = stringResource(R.string.icon_search)
                            //,colorFilter = ColorFilter.tint(Color.Green)
                        )
                    }
                    Text(
                        text = "${convertTempToInt(currentDay.value.minTemp)}C°/" +
                                "${convertTempToInt(currentDay.value.maxTemp)}C°",
                        fontSize = TextUnit(24f, TextUnitType.Sp),
                        color = Color.White,
                    )

                    IconButton(onClick = {
                        onClickSync.invoke()
                    }) {
                        Image(
                            modifier = Modifier.size(36.dp),
                            painter = painterResource(id = R.drawable.ic_synck),
                            contentDescription = stringResource(R.string.icon_syncs)
                        )
                    }

                }

            }

        }

    }

}

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun TabLayout(dayList: MutableState<List<WeatherModel>>, currentDay: MutableState<WeatherModel>) {

    val tabList = listOf(
        stringResource(R.string.hours), stringResource(R.string.days)
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ){
        2
    }
    val tabIndex = pagerState.currentPage
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(top = 4.dp)
    ) {
        TabRow(modifier = Modifier
            .alpha(ALPHA_SCREEN)
            .clip(
                RoundedCornerShape(ROUNDED_CORNER.dp)
            ),
            selectedTabIndex = tabIndex,
            containerColor = Color(0xFF4F32FF),
            indicator = {
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(it[tabIndex])
                )
            }) {
            tabList.forEachIndexed { index, name ->
                Tab(selected = false, onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }, text = {
                    Text(
                        text = name,
                        color = Color.White,
                        fontSize = TextUnit(24f, TextUnitType.Sp),
                    )
                })
            }
        }
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1.0f),
        ) { tabIndex ->
            val listWeatherForecast=when(tabIndex){
                0-> getWeatherByHours(currentDay.value.hours)
                1-> {
                    dayList.value
                }
                else -> dayList.value
            }

            MainList(listWeatherForecast, currentDay = currentDay)
        }
    }
}
