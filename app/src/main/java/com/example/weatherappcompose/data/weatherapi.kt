package com.example.weatherappcompose.data

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