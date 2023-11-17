package com.gmail.redballtoy.sdweatherone.utils

import androidx.compose.runtime.Composable
import com.gmail.redballtoy.sdweatherone.data.WeatherModel
import kotlin.math.roundToInt


@Composable
fun getTemperature(item: WeatherModel): String {

    if (item.currentTemp.isEmpty()) {
        return "${setTempSign(convertTempToInt(item.minTemp))}${convertTempToInt(item.minTemp)}°C/" +
                "${setTempSign(convertTempToInt(item.maxTemp))}${convertTempToInt(item.maxTemp)}°C"
    }
    return "${setTempSign(convertTempToInt(item.currentTemp))}${convertTempToInt(item.currentTemp)}°C"
}

fun setTempSign(item: String): String {
    if (item.toFloat() > 0) return "+"
    return ""
}

fun convertTempToInt(temp: String): String {
    return if (temp.isEmpty()) {
        ""
    } else {
        temp.toFloat().roundToInt().toString()
    }
}
