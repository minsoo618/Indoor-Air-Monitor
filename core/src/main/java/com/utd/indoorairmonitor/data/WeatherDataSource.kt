package com.utd.indoorairmonitor.data

import com.utd.indoorairmonitor.domain.Weather

interface WeatherDataSource {
    suspend fun fetchData(): Weather

    fun getTemp() : Double

    fun getHumidity() : Int

    fun setZipCode(zipCode: String)
}