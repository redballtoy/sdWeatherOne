package com.gmail.redballtoy.sdweatherone.utils

fun getChangeOfPricitipation(
    chanceOfRain: String,
    chanceOfSnow: String
): String {
    if (chanceOfRain.isEmpty() or chanceOfSnow.isEmpty()) return ""
    return if (
        chanceOfRain.toInt() > chanceOfSnow.toInt()
    ) chanceOfRain else chanceOfSnow
}