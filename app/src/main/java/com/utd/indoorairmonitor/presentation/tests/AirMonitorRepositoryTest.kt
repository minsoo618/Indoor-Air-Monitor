package com.utd.indoorairmonitor.presentation.tests

import com.utd.indoorairmonitor.framework.purpleAirMonitor.PurpleAirMonitorAPI
import org.junit.Assert
import org.junit.Test


class AirMonitorRepositoryTest {
    private val a = PurpleAirMonitorAPI()

    @Test // Check if returned value of PM2_5 is a double
    fun getPM2_5() {
        val testX = a.getPM2_5()
        Assert.assertTrue("Not a double", testX is Double)

        Assert.assertEquals(0.0, a.getPM2_5(), 0.0)
    }

    @Test // Check if returned value of PM10_0 is a double
    fun getPM10_0() {
        val testY = a.getPM10_0()
        Assert.assertTrue("Not a double", testY is Double)

        Assert.assertEquals(0.0, a.getPM10_0(), 0.0)
    }

}