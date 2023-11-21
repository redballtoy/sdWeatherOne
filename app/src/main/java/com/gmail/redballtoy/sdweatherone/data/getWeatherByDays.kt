package com.gmail.redballtoy.sdweatherone.data

import android.util.Log
import com.gmail.redballtoy.sdweatherone.repository.myLog
import org.json.JSONObject


fun getWeatherByDays(response: String): List<WeatherModel> {
    val wmList = ArrayList<WeatherModel>()
    if (response.isEmpty()) {
        return listOf()
    } else {
        val mainObject = JSONObject(response)
        val locationObj = mainObject.getJSONObject("location")
        Log.d(myLog,"locationObj = $locationObj")
        val currentDayObj = mainObject.getJSONObject("current")
        Log.d(myLog,"currentDayObj = $currentDayObj")
        val forecastDays = mainObject.getJSONObject("forecast").getJSONArray("forecastday")
        Log.d(myLog,"forecastDays = ${forecastDays[0]}")

        for (i in 0 until forecastDays.length()) {
            val item = forecastDays[i] as JSONObject
            wmList.add(
                WeatherModel(
                    item.getString("date").toString().replace("-","."),
                    locationObj.getString("name"),
                    locationObj.getString("region"),
                    currentDayObj.getString("last_updated").toString().replace("-","."),
                    if (i == 0) currentDayObj.getString("temp_c") else "",
                    if (i == 0) currentDayObj.getString("feelslike_c") else "",
                    item.getJSONObject("day").getJSONObject("condition").getString("text"),
                    item.getJSONObject("day").getString("maxtemp_c"),
                    item.getJSONObject("day").getString("mintemp_c"),
                    item.getJSONArray("hour").toString(),
                    ""
                )
            )
        }

    }
    return wmList
}