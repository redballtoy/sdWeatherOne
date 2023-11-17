package com.gmail.redballtoy.sdweatherone

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gmail.redballtoy.sdweatherone.ui.theme.SdWeatherOneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SdWeatherOneTheme {
            }
        }
    }
}


