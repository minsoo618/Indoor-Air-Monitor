package com.utd.indoorairmonitor.data

class PeakFlowRepository(private val dataSource: PeakFlowDataSource) {
    fun getPEFR() = dataSource.getPEFR()

    fun setPEFR(peakFlowRate: Double) = dataSource.setPEFR(peakFlowRate)
}