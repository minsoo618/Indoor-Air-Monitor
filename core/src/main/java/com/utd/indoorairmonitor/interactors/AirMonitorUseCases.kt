package com.utd.indoorairmonitor.interactors

import com.utd.indoorairmonitor.data.AirMonitorRepository

class AirMonitorUseCases (private val repository: AirMonitorRepository) {
    suspend fun fetchData() = repository.fetchData()

    fun getPM2_5() = repository.getPM2_5()

    fun getPM10_0() = repository.getPM10_0()

    fun setID(id: String) = repository.setID(id)

    fun getID(): String = repository.getID()
}