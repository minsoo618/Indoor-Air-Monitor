package com.utd.indoorairmonitor.data

interface PeakFlowDataSource {
    fun getPEFR(): Double

    fun setPEFR(peakFlowRate: Double)
}