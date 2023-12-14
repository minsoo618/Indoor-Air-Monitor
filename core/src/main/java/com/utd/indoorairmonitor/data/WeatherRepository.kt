package com.utd.indoorairmonitor.data

class WeatherRepository (private val dataSource: WeatherDataSource) {
    suspend fun fetchData() = dataSource.fetchData()

    fun getTemp() = dataSource.getTemp()

    fun getHumidity() = dataSource.getHumidity()

    fun setZipCode(zipCode: String) = dataSource.setZipCode(zipCode)
}