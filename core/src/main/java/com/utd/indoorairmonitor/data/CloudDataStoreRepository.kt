package com.utd.indoorairmonitor.data

import com.utd.indoorairmonitor.domain.DataStore

class CloudDataStoreRepository(private val dataSource: CloudDataStoreDataSource) {
    fun add(dataStore: DataStore) = dataSource.add(dataStore)

    fun getAll() = dataSource.getAll()

    fun getLatest() = dataSource.getLatest()

    fun remove(dataStore: DataStore) = dataSource.remove(dataStore)
}