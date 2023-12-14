package com.utd.indoorairmonitor.framework.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.utd.indoorairmonitor.framework.db.RoomDataStoreEntity

@Dao
interface RoomDataStoreDAO {

    @Insert
    fun add(dataStore: RoomDataStoreEntity)

    @Query("SELECT * FROM data_store")
    fun getAll(): List<RoomDataStoreEntity>

    @Query("SELECT * FROM data_store ORDER BY id DESC LIMIT 1")
    fun getLatest(): RoomDataStoreEntity

    @Query("SELECT * FROM data_store ORDER BY id ASC LIMIT 1")
    fun getOldest(): RoomDataStoreEntity

    @Query("SELECT COUNT(*) FROM data_store")
    fun count(): Int

    @Delete
    fun remove(dataStore: RoomDataStoreEntity)
}