package com.utd.indoorairmonitor.data

class AirMonitorRepository(private val dataSource: AirMonitorDataSource) {
    suspend fun fetchData() = dataSource.fetchData()

    fun getPM2_5() = dataSource.getPM2_5()

    fun getPM10_0() = dataSource.getPM10_0()

    fun setID(id: String) = dataSource.setID(id)

    fun getID(): String = dataSource.getID()
}