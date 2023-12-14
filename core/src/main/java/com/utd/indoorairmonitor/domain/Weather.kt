package com.utd.indoorairmonitor.domain

// Temperature in *C
data class Weather(var zipCode: String,
                   var temperature: Double,
                   var humidity: Int,
                   var weatherID: Int)
