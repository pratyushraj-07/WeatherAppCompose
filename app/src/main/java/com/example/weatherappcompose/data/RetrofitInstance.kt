package com.example.weatherappcompose.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.weatherbit.io"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api2 : WeatherApi2 by lazy {
        retrofit.create(WeatherApi2::class.java)
    }

    val api: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

}