package com.gmail.redballtoy.sdweatherone.utils

import com.gmail.redballtoy.sdweatherone.R

fun getWeatherIcon(weatherCondition: String): Int {
    return when (weatherCondition) {
        "Patchy rain possible" -> R.drawable.rain_with_sun
        "Light freezing rain" -> R.drawable.rain
        "Partly cloudy" -> R.drawable.cloud
        "Overcast" -> R.drawable.cloud
        "Cloudy" -> R.drawable.cloud
        "Light snow" -> R.drawable.light_snow
        "Light snow showers" -> R.drawable.light_snow
        "Moderate or heavy snow showers" -> R.drawable.hard_snow
        else -> R.drawable.sunny
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