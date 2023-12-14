package com.utd.indoorairmonitor.data

import com.utd.indoorairmonitor.domain.DataStore

class RoomDataStoreRepository(private val dataSource: RoomDataSource) {
    fun add(dataStore: DataStore) = dataSource.add(dataStore)

    fun getAll() = dataSource.getAll()

    fun getLatest() = dataSource.getLatest()

    fun remove(dataStore: DataStore) = dataSource.remove(dataStore)
}