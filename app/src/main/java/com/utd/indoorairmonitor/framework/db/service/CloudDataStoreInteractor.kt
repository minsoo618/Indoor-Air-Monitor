package com.utd.indoorairmonitor.framework.db.service

import android.content.Context
import com.utd.indoorairmonitor.data.CloudDataStoreDataSource
import com.utd.indoorairmonitor.domain.DataStore

class CloudDataStoreInteractor(val context: Context) : CloudDataStoreDataSource {
    override fun add(dataStore: DataStore) {
        TODO("Not yet implemented")
    }

    override fun getAll(): List<DataStore> {
        TODO("Not yet implemented")
    }

    override fun getLatest(): DataStore {
        TODO("Not yet implemented")
    }

    override fun getOldest(): DataStore {
        TODO("Not yet implemented")
    }

    override fun count(): Int {
        TODO("Not yet implemented")
    }

    override fun remove(dataStore: DataStore) {
        TODO("Not yet implemented")
    }

}