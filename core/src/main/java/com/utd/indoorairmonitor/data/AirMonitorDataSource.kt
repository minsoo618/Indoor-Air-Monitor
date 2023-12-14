package com.utd.indoorairmonitor.data

import com.utd.indoorairmonitor.domain.AirMonitor

interface AirMonitorDataSource {
    suspend fun fetchData(): AirMonitor

    fun getPM2_5(): Double

    fun getPM10_0(): Double

    fun setID(id: String)

    fun getID(): String
}