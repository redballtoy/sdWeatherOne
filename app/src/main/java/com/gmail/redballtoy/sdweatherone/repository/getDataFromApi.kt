package com.gmail.redballtoy.sdweatherone.repository

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gmail.redballtoy.sdweatherone.compose.API_KEY
import com.gmail.redballtoy.sdweatherone.data.WeatherModel
import com.gmail.redballtoy.sdweatherone.data.getWeatherByDays

const val myLog = "myLog"

fun getDataFromApi(
    cityOrCoordinate:String,
    context: Context,
    daysList: MutableState<List<WeatherModel>>,
    currentDay: MutableState<WeatherModel>
) {
    val url = "https://api.weatherapi.com/v1/forecast.json?key=$API_KEY" +
            "&q=${cityOrCoordinate}" +
            "&days=" +
            "10" +
            "&aqi=no&alerts=no"
    val queue = Volley.newRequestQueue(context)
    val sRequest = StringRequest(
        Request.Method.GET,
        url,
        { response ->
            Log.d(myLog, "Response: $response")
            val list = getWeatherByDays(response)
            daysList.value = list
            currentDay.value=list[0]
        },
        {
            Log.d(myLog, "VolleyError:$it ")
        }
    )
    queue.add(sRequest)
}