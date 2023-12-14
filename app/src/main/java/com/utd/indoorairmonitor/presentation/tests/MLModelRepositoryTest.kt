package com.utd.indoorairmonitor.presentation.tests

import com.utd.indoorairmonitor.domain.MLModel
import com.utd.indoorairmonitor.framework.asthmaMLModel.AsthmaMLModel
import org.junit.Assert
import org.junit.Test

class MLModelRepositoryTest {
    private val m = AsthmaMLModel()

    @Test
    fun getMLResults() { // Ensures getMLResults returns an MLModel
        val MLTester = m.getModel()
        Assert.assertTrue("MLModel assertion error", MLTester is MLModel)

        // Check if ML contains data. If not, print message
        Assert.assertNotNull("Contains no data", MLTester)
    }

    @Test
    fun predictMLResults(){
        /*               TO-DO
        * Use mock testing or another form of unit testing to test specific
        * values for predicting the ML results. Code below is what failed to test values.
        * Dependencies and imports for mock testing included in the app.grade and this Test file.
        * Can be easily translatable to other unit tests to test specific values once one
        * mock test is implemented.
        *
        *
        * //val ke : AsthmaMLModel = mock()
        * val ke =  mock(AsthmaMLModel::class.java)
        * ke.predictMLResults(0.0,0,2.0,0.0)
        * Mockito.`when`(ke.predictMLResults(0.0,0,2.0,0.0))
        * Mockito.when(ke.predictMLResults(0.0,0,0.0,0.0)).thenReturn(0.0)
        * Mockito.`when`(ke.getMLOutput1()).thenReturn(0.0)
        *
        */
    }

    @Test
    fun getMLOutput1() {
        val testX = m.getResult()
        Assert.assertTrue("MLOutput1 Not a double", testX is Double)
    }

    @Test
    fun getMLOutput2() {
        val testY = m.getMLOutput2()
        Assert.assertTrue("MLOutput2 Not a double", testY is Double)
    }
}