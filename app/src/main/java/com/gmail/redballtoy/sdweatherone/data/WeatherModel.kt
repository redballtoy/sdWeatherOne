package com.gmail.redballtoy.sdweatherone.data

data class WeatherModel(
    val time:String,
    val city: String,
    val region: String,
    val lastUpdatedTime: String,
    val currentTemp: String,
    val feelsLikeTemp: String,
    val weatherCondition: String,
    val maxTemp: String,
    val minTemp: String,
    val hours:String,
    val changeOfPrecipitation: String
)