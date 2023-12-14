package com.utd.indoorairmonitor.data

import com.utd.indoorairmonitor.domain.MLModel

interface MLModelDataSource {
    fun predictResult(temperature: Double, humidity: Int, pm2_5: Double, pm10_0: Double)

    fun getModel() : MLModel

    fun getResult() : Double

    fun getMLOutput2() : Double
}