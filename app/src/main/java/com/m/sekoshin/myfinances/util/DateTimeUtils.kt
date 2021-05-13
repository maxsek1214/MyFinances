package com.m.sekoshin.myfinances.util

import java.util.*

fun getHour(date: Date): Int {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date.time
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun getMinutes(date: Date): Int {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date.time
    return calendar.get(Calendar.MINUTE)
}

fun setTime(date: Date, hour: Int, minutes: Int): Date {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = date.time
    calendar.set(Calendar.HOUR_OF_DAY, hour)
    calendar.set(Calendar.MINUTE, minutes)
    return Date(calendar.timeInMillis)
}

fun setDate(date: Date, datetime: Long): Date {
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = datetime
    calendar.set(Calendar.HOUR_OF_DAY, getHour(date))
    calendar.set(Calendar.MINUTE, getMinutes(date))
    return Date(calendar.timeInMillis)
}