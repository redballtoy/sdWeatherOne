package com.gmail.redballtoy.sdweatherone.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.gmail.redballtoy.sdweatherone.data.WeatherModel
import com.gmail.redballtoy.sdweatherone.ui.theme.BlueLight
import com.gmail.redballtoy.sdweatherone.utils.getTemperature
import com.gmail.redballtoy.sdweatherone.utils.getWeatherIcon

@Composable
fun WeatherListItem(item: WeatherModel) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .alpha(ALPHA_SCREEN)
            .padding(top = 2.dp),
        colors = CardDefaults.cardColors(BlueLight),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(ROUNDED_CORNER.dp / 2)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            Column {
                Text(
                    text = item.time,
                    color = Color.White
                )
                Text(
                    text = item.weatherCondition,
                    color = Color.White
                )
            }
            Text(
                //ifEmpty true forecast by hours otherwise by days
                text = getTemperature(item),
                color = Color.White,
                fontSize = TextUnit(28f, TextUnitType.Sp)
            )
            Image(
                modifier = Modifier.size(34.dp),
                painter = painterResource(getWeatherIcon(item.weatherCondition)),
                contentDescription = "$item.weatherCondition"
            )
            Text(text = "80%",
                color = Color.White,
                fontSize = TextUnit(14f, TextUnitType.Sp)
            )
        }

    }
}

