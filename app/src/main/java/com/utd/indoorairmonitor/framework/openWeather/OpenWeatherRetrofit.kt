package com.utd.indoorairmonitor.framework.openWeather

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import com.utd.indoorairmonitor.framework.openWeather.Model.OpenWeatherModel

interface OpenWeatherRetrofit {

    @GET("data/2.5/weather")
    fun getWeatherData(
            @Query("zip") zipCode: String,
            @Query("appid") app_id: String
    ): Call<OpenWeatherModel>

    // Our API key is 5e6528a80e5479b2d769ef5801c6b122. Found in openweather.org account.
    companion object {
        operator fun invoke(): OpenWeatherRetrofit {
            return Retrofit.Builder()
                    .baseUrl("https://api.openweathermap.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OpenWeatherRetrofit::class.java)
        }
    }
}