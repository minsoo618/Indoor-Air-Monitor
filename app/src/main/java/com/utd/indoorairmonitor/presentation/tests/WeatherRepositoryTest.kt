package com.utd.indoorairmonitor.presentation.tests


import com.utd.indoorairmonitor.framework.openWeather.OpenWeatherAPI
import org.junit.Assert
import org.junit.Test

class WeatherRepositoryTest {
    private val w = OpenWeatherAPI()

    @Test
    fun getTemp() {
        val tempTester = w.getTemp()
        Assert.assertTrue("Temp not of Double value", tempTester is Double)

        // Check if temp contains data. If not, print message
        Assert.assertNotNull("Contains no data", tempTester)
    }

    @Test
    fun getHumidity() {
        val humidTester = w.getHumidity()
        Assert.assertTrue("Humidity not of Int value", humidTester is Int)

        // Check if humidity contains data. If not, print message
        Assert.assertNotNull("Contains no data", humidTester)
    }
}