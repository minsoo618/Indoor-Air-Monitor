package com.utd.indoorairmonitor.domain

// ID should be part of AirMonitor because it is hard to separate
data class AirMonitor(var ID: String,
                      var pm2_5: Double,
                      var pm10_0: Double)