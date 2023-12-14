package com.utd.indoorairmonitor.framework.openWeather

import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.utd.indoorairmonitor.data.WeatherDataSource
import com.utd.indoorairmonitor.domain.Weather
import com.utd.indoorairmonitor.framework.openWeather.Model.OpenWeatherModel
import java.text.DecimalFormat

class OpenWeatherAPI: WeatherDataSource {
    private var openWeather = Weather("00000", 0.0, 0, 0)
    private val APIKEY = "28514743643fbe379b640a1b414a64dc" //Make one for yourself.

    override fun getTemp() = openWeather.temperature
    override fun getHumidity() = openWeather.humidity
    override fun setZipCode(zipCode: String) {
        openWeather.zipCode = zipCode
    }

    private val openWeatherApi = OpenWeatherRetrofit()
    override suspend fun fetchData(): Weather {
        val openWeatherApiCall = openWeatherApi.getWeatherData(openWeather.zipCode, APIKEY)

        openWeatherApiCall.enqueue(object: Callback<OpenWeatherModel> {
            override fun onFailure(call: Call<OpenWeatherModel>, t: Throwable) {
                //Log.e("CALL ERROR WEATHER", t.message.toString())
            }

            override fun onResponse(call: Call<OpenWeatherModel>, response: Response<OpenWeatherModel>) {
                if (response.isSuccessful) {
                    val body : OpenWeatherModel = response.body()!!
                    openWeather.humidity = body.main.humidity
                    val kelvin = body.main.temp
                    val fahrenheit = (kelvin - 273.15) * 1.8 + 32
                    val formatTemp = DecimalFormat(".###")
                    openWeather.temperature =  formatTemp.format(fahrenheit).toDouble()
                    openWeather.weatherID = body.weather[0].id
                    // TODO: remove
                    Log.e("Weather ID", openWeather.weatherID.toString());
                }
                else{
                    //Log.e("RESP ERROR WEATHER", response.errorBody().toString())
                }
            }
        })

        return openWeather
    }
}
