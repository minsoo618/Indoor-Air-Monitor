package com.utd.indoorairmonitor.data

class MLModelRepository(private val dataSource: MLModelDataSource) {
    fun predictResult(temperature: Double, humidity: Int, pm2_5: Double, pm10_0: Double)
            = dataSource.predictResult(temperature, humidity, pm2_5, pm10_0)

    fun getModel() = dataSource.getModel()

    fun getResult() = dataSource.getResult()

    fun getMLOutput2() = dataSource.getMLOutput2()
}