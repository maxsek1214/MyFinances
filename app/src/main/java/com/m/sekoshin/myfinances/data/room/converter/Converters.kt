package com.m.sekoshin.myfinances.data.room.converter

import androidx.room.TypeConverter
import java.util.*

class Converters {

    @TypeConverter
    fun toDate(timestamp: Long?): Date? {
        return timestamp?.let { Date(it) }
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }

}