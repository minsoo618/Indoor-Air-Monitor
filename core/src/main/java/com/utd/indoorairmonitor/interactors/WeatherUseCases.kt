package com.utd.indoorairmonitor.interactors

import com.utd.indoorairmonitor.data.WeatherRepository

class WeatherUseCases (private val repository: WeatherRepository) {
    suspend fun fetchData() = repository.fetchData()

    fun getTemp() = repository.getTemp()

    fun getHumidity() = repository.getHumidity()

    fun setZipCode(zipCode: String) = repository.setZipCode(zipCode)
}