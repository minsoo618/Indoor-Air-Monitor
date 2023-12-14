package com.utd.indoorairmonitor.interactors

import com.utd.indoorairmonitor.data.MLModelRepository

class MLModelUseCases (private val repository: MLModelRepository) {
    fun predictResult(temperature: Double, humidity: Int, pm2_5: Double, pm10_0: Double)
            = repository.predictResult(temperature, humidity, pm2_5, pm10_0)

    fun getModel() = repository.getModel()

    fun getResult() = repository.getResult()

    fun getMLOutput2() = repository.getMLOutput2()
}