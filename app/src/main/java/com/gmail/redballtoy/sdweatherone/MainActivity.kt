package com.gmail.redballtoy.sdweatherone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.gmail.redballtoy.sdweatherone.compose.DialogSearch
import com.gmail.redballtoy.sdweatherone.compose.MainCardComp
import com.gmail.redballtoy.sdweatherone.compose.TabLayout
import com.gmail.redballtoy.sdweatherone.data.WeatherModel
import com.gmail.redballtoy.sdweatherone.repository.getDataFromApi
import com.gmail.redballtoy.sdweatherone.ui.theme.SdWeatherOneTheme
import com.gmail.redballtoy.sdweatherone.utils.getBackWeatherIcon

class MainActivity : ComponentActivity() {

    var city: String? = null
    var gistitude: String? = "55.899146,37.620878"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SdWeatherOneTheme {
                val daysList = remember {
                    mutableStateOf(listOf<WeatherModel>())
                }
                val dialogState = remember {
                    mutableStateOf(false)
                }
                if (dialogState.value) {
                    DialogSearch(dialogState, onSubmit = {

                    })
                }

                val currentDay = remember {
                    mutableStateOf(
                        WeatherModel(
                            "", "", "", "", "", "",
                            "", "", "", "", ""
                        )
                    )
                }
                getDataFromApi(city, gistitude, this, daysList, currentDay)
                Image(
                    painter = painterResource(getBackWeatherIcon(currentDay.value.weatherCondition)),
                    contentDescription = currentDay.value.weatherCondition,
                    modifier = Modifier.fillMaxSize(),
                    //add stretch on screen
                    contentScale = ContentScale.Crop
                )

                Column(
                    modifier = Modifier.padding(4.dp)
                ) {
                    MainCardComp(currentDay,
                        onClickSync = {
                            getDataFromApi(city, gistitude, this@MainActivity, daysList, currentDay)
                        },
                        onClickSearch = {
                            dialogState.value = true
                        })
                    TabLayout(daysList, currentDay)
                }
            }
        }
    }
}



