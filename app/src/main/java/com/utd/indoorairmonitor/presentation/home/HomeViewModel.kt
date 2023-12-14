package com.utd.indoorairmonitor.presentation.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.utd.indoorairmonitor.domain.AirMonitor
import com.utd.indoorairmonitor.domain.DataStore
import com.utd.indoorairmonitor.domain.PeakFlow
import com.utd.indoorairmonitor.domain.Weather
import com.utd.indoorairmonitor.framework.Interactors
import com.utd.indoorairmonitor.framework.ViewModel
import kotlinx.coroutines.*
import java.sql.*

class HomeViewModel(application: Application, interactors: Interactors):
    ViewModel(application, interactors){
    private val weatherUseCases = interactors.weatherUseCases
    private val airMonitorUseCases = interactors.airMonitorUseCases
    private val peakFlowUseCases = interactors.PeakFlowUseCases

    // Peak flow meter private field
    private val _pefr = MutableLiveData<PeakFlow>(PeakFlow(0.0))

    // Air monitor related private fields
    private val _pm2_5 = MutableLiveData<Double>(0.0)
    private val _pm10_0 = MutableLiveData<Double>(0.0)
    private val _airMonitor = MutableLiveData<AirMonitor>(AirMonitor("0",0.0,0.0))

    // ML model related private fields
    private val _mlResult = MutableLiveData<Double>(0.0)
    private val _output2 = MutableLiveData<Double>(0.0)

    // Weather related private fields
    private val _humidity = MutableLiveData<Int>(0)
    private val _temperature = MutableLiveData<Double>(0.0)
    private val _weatherID = MutableLiveData<Int>(0)
    private val _weather = MutableLiveData<Weather>(Weather("0",0.0,0, 0))

    // Peak flow meter getter
    val pefr: LiveData<PeakFlow>
        get() = _pefr

    // Air monitor related private fields getters
    val pm2_5 : LiveData<Double>
        get() = _pm2_5
    val pm10_0 : LiveData<Double>
        get() = _pm10_0
    val airMonitor : LiveData<AirMonitor>
        get() = _airMonitor

    // ML model related private fields getters
    val mlResult : LiveData<Double>
        get() = _mlResult
    val output2 : LiveData<Double>
        get() = _output2


    // Weather related private fields getter
    val humidity : LiveData<Int>
        get() = _humidity
    val temperature : LiveData<Double>
        get() = _temperature
    val weatherID : LiveData<Int>
        get() = _weatherID
    val weather : LiveData<Weather>
        get() = _weather

    fun setPEFR(pefr: Double) = peakFlowUseCases.setPEFR(pefr)
    fun getPEFR() : Double = peakFlowUseCases.getPEFR()

    fun setAirMonitorID(name: String) = airMonitorUseCases.setID(name)

    fun fetchAirMonitorData() {
        GlobalScope.launch {
            runBlocking {
                _airMonitor.postValue(airMonitorUseCases.fetchData())
            }
            _pm2_5.postValue(_airMonitor.value!!.pm2_5)
            _pm10_0.postValue(_airMonitor.value!!.pm10_0)
        }
    }

    fun setZipCode(zipCode: String) = weatherUseCases.setZipCode(zipCode)

    fun fetchWeatherData() {
        GlobalScope.launch {
            runBlocking {
                _weather.postValue(weatherUseCases.fetchData())
            }
            _humidity.postValue(_weather.value!!.humidity)
            _temperature.postValue(_weather.value!!.temperature)
            _weatherID.postValue(_weather.value!!.weatherID)
        }
    }

    fun predictMLResult(){
        val temp = weatherUseCases.getTemp()
        val humidity = weatherUseCases.getHumidity()
        val pm2_5 = airMonitorUseCases.getPM2_5()
        val pm10_0 = airMonitorUseCases.getPM10_0()

        val mlModelUseCases = interactors.mlModelUseCases
        mlModelUseCases.predictResult(temp, humidity, pm2_5, pm10_0)

        _mlResult.value = mlModelUseCases.getResult()
        _output2.value = mlModelUseCases.getMLOutput2()

        saveData()
    }

    private fun saveData() {
        val questionnaireUseCases = interactors.questionnaireUseCases
        val questionnaire = questionnaireUseCases.getAnswer()
        val dataStore = DataStore(
                id = 0,
                date = Date(System.currentTimeMillis()),
                time = Time(System.currentTimeMillis()),
                airQualityDeviceId = airMonitorUseCases.getID(),
                airQualityPM2_5 = airMonitorUseCases.getPM2_5().toFloat(),
                airQualityPM10_0 = airMonitorUseCases.getPM10_0().toFloat(),
                peakFlowValue = peakFlowUseCases.getPEFR().toFloat(),
                questionnaireAnswers1 = questionnaire[0],
                questionnaireAnswers2 = questionnaire[1],
                questionnaireAnswers3 = questionnaire[2],
                questionnaireAnswers4 = questionnaire[3],
                questionnaireAnswers5 = questionnaire[4],
                questionnaireAnswers6 = questionnaire[5],
                weatherTemperature = weatherUseCases.getTemp().toFloat(),
                weatherHumidity = weatherUseCases.getHumidity().toFloat(),)

        val roomDBUseCases = interactors.roomDBUseCases
        val cloudDBUseCases = interactors.cloudDBUseCases

        roomDBUseCases.add(dataStore)
        //doesn't work so is commented out
        //cloudDBUseCases.add(dataStore)
    }

}