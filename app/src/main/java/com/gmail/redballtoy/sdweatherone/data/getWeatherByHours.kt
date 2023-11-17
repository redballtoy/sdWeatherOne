package com.gmail.redballtoy.sdweatherone.data

import com.gmail.redballtoy.sdweatherone.utils.convertTempToInt
import com.gmail.redballtoy.sdweatherone.utils.getChangeOfPricitipation
import org.json.JSONArray
import org.json.JSONObject


fun getWeatherByHours(hours: String): List<WeatherModel> {
    if (hours.isEmpty()) return listOf()
    val hoursArray = JSONArray(hours)
    val list = ArrayList<WeatherModel>()
    for (i in 0 until hoursArray.length()) {
        val item = hoursArray[i] as JSONObject
        list.add(
            WeatherModel(
                item.getString("time"),
                "",
                "",
                item.getString("time"),
                convertTempToInt(item.getString("temp_c")),
                convertTempToInt(item.getString("feelslike_c")),
                item.getJSONObject("condition").getString("text"),
                "",
                "",
                "",
                getChangeOfPricitipation(
                    item.getString("chance_of_rain"),
                    item.getString("chance_of_snow")
                )
            )
        )
    }
    return list
}
