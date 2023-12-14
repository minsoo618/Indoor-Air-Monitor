package com.utd.indoorairmonitor.domain

import java.sql.Date
import java.sql.Time

data class DataStore(var id: Long,
                     var date: Date,
                     var time: Time,
                     var airQualityDeviceId: String,
                     var airQualityPM2_5: Float,
                     var airQualityPM10_0: Float,
                     var peakFlowValue: Float,
                     var questionnaireAnswers1: Int,
                     var questionnaireAnswers2: Int,
                     var questionnaireAnswers3: Int,
                     var questionnaireAnswers4: Int,
                     var questionnaireAnswers5: Int,
                     var questionnaireAnswers6: Int,
                     var weatherTemperature: Float,
                     var weatherHumidity: Float)