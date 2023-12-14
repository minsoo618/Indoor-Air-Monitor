package com.utd.indoorairmonitor.presentation.tests

import com.utd.indoorairmonitor.framework.peakflow.PeakFlowDataSourceImpl
import org.junit.Assert
import org.junit.Test

class PeakFlowRepositoryTest {
    private val p = PeakFlowDataSourceImpl()

    @Test
    fun getPeakFlow() {
        val pTester = p.getPEFR()
        Assert.assertTrue("Peakflow value is not of type Double", pTester is Double)

        // Check if peakflow contains data or not. If not , print msg
        Assert.assertNotNull("Contains no data", pTester)
    }
}