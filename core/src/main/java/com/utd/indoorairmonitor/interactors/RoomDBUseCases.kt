package com.utd.indoorairmonitor.interactors

import com.utd.indoorairmonitor.data.RoomDataStoreRepository
import com.utd.indoorairmonitor.domain.DataStore

class RoomDBUseCases (private val repository: RoomDataStoreRepository) {
    fun add(dataStore: DataStore) = repository.add(dataStore)

    fun getAll() = repository.getAll()

    fun getLatest() = repository.getLatest()

    fun remove(dataStore: DataStore) = repository.remove(dataStore)
}