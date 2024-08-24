package com.example.weatherappcompose.data

import com.example.weatherappcompose.data.models.WeatherModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v2.0/current")
    suspend fun getWeather(
        @Query("city") city : String,
        @Query("key") apikey : String
    ) : Response<WeatherModel>

}

interface WeatherApi2 {
    @GET("/v2.0/current")
    suspend fun getCurWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apikey: String
    ): Response<WeatherModel>
}

