package com.utd.indoorairmonitor.interactors

import com.utd.indoorairmonitor.data.PeakFlowRepository

class PeakFlowUseCases (private val repository: PeakFlowRepository) {
    fun getPEFR() = repository.getPEFR()

    fun setPEFR(peakFlowRate: Double) = repository.setPEFR(peakFlowRate)
}