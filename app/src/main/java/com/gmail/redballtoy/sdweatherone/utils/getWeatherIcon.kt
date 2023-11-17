package com.gmail.redballtoy.sdweatherone.utils

import com.gmail.redballtoy.sdweatherone.R

fun getWeatherIcon(weatherCondition: String): Int {
    return when (weatherCondition) {
        "Patchy rain possible" -> R.drawable.i_rain
        "Light freezing rain" -> R.drawable.i_rain
        "Partly cloudy" -> R.drawable.i_cloudly
        else -> R.drawable.i_clear
    }
}

fun getBackWeatherIcon(weatherCondition: String): Int {
    return when (weatherCondition) {
        "Patchy rain possible" -> R.drawable.cloudly_back
        "Light freezing rain" -> R.drawable.cloudly_back
        "Partly cloudy" -> R.drawable.cloudly_back
        else -> R.drawable.sunny_back
    }
}