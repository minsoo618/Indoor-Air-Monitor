package com.utd.indoorairmonitor.framework.peakflow

import com.utd.indoorairmonitor.data.PeakFlowDataSource
import com.utd.indoorairmonitor.domain.PeakFlow

class PeakFlowDataSourceImpl: PeakFlowDataSource {

    private var pefr: PeakFlow = PeakFlow(0.0)

    override fun getPEFR(): Double = pefr.peakFlowRate

    override fun setPEFR(peakFlowRate: Double) {
        pefr.peakFlowRate = peakFlowRate
    }

}