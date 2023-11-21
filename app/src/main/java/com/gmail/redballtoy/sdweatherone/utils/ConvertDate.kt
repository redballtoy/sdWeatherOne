package com.gmail.redballtoy.sdweatherone.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

fun String.convertDate():String{
    val pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val localDateTime = LocalDateTime.parse(this, pattern)
    val format = SimpleDateFormat("dd.MMM.yy HH:mm", Locale.getDefault())
    return format.format(localDateTime)
}