package com.utd.indoorairmonitor.framework.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.utd.indoorairmonitor.framework.db.dao.RoomDataStoreDAO
import com.utd.indoorairmonitor.framework.db.utility.Converters

@Database(
        entities = [RoomDataStoreEntity::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(Converters::class)

abstract class RoomDataStoreDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "reader.db"

        private var instance: RoomDataStoreDatabase? = null

        private fun create(context: Context): RoomDataStoreDatabase =
                Room.databaseBuilder(context, RoomDataStoreDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()

        fun getInstance(context: Context): RoomDataStoreDatabase =
                (instance ?: create(context)).also { instance = it }
    }

    abstract fun dataStoreDao(): RoomDataStoreDAO

}