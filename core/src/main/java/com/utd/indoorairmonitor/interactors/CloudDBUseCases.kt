package com.utd.indoorairmonitor.interactors

import com.utd.indoorairmonitor.data.CloudDataStoreRepository
import com.utd.indoorairmonitor.domain.DataStore

class CloudDBUseCases (private val repository: CloudDataStoreRepository) {
    fun add(dataStore: DataStore) = repository.add(dataStore)

    fun getAll() = repository.getAll()

    fun getLatest() = repository.getLatest()

    fun remove(dataStore: DataStore) = repository.remove(dataStore)
}