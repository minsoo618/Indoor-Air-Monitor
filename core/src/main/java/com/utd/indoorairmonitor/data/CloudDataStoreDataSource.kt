package com.utd.indoorairmonitor.data

import com.utd.indoorairmonitor.domain.DataStore

interface CloudDataStoreDataSource {
    fun add(dataStore: DataStore)

    fun getAll(): List<DataStore>

    fun getLatest(): DataStore

    fun getOldest(): DataStore

    fun count(): Int

    fun remove(dataStore: DataStore)
}