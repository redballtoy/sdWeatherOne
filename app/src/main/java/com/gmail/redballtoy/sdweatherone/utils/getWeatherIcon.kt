package com.gmail.redballtoy.sdweatherone.utils

import com.gmail.redballtoy.sdweatherone.R

fun getWeatherIcon(weatherCondition: String): Int {
    return when (weatherCondition) {
        "Patchy rain possible" -> R.drawable.rain_with_sun
        "Light freezing rain" -> R.drawable.rain
        "Partly cloudy" -> R.drawable.cloudly
        "Overcast" -> R.drawable.cloud
        "Cloudy" -> R.drawable.cloud
        "Light snow" -> R.drawable.light_snow
        "Heavy snow" -> R.drawable.hard_snow
        "Light snow showers" -> R.drawable.light_snow
        "Moderate or heavy snow showers" -> R.drawable.hard_snow
        else -> R.drawable.sunny
    }
}

fun getBackWeatherIcon(weatherCondition: String): Int {
    return when (weatherCondition) {
        "Clear" -> R.drawable.sunny_back
        else -> R.drawable.cloudly_back
    }
}