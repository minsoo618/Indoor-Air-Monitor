package com.utd.indoorairmonitor.framework.db.utility

import androidx.room.TypeConverter
import java.sql.Date
import java.sql.Time

object Converters {
    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun timeFromTimeStamp(value: Long?): Time? {
        return value?.let { Time(it) }
    }

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    @JvmStatic
    fun timeToTimestamp(time: Time?): Long? {
        return time?.time
    }
}