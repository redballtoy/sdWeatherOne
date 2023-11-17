package com.gmail.redballtoy.sdweatherone.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import com.gmail.redballtoy.sdweatherone.data.WeatherModel

@Composable
fun MainList(list:List<WeatherModel>, currentDay: MutableState<WeatherModel>){
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(
            list
        ) { _, item ->
            WeatherListItem(item = item)
        }

    }
}