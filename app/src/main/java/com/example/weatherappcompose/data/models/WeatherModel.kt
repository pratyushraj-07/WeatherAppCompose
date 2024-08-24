package com.example.weatherappcompose.data.models

data class WeatherModel(
    val count: String,
    val data: List<Data>
)

data class Data(
    val app_temp: String,
    val aqi: String,
    val city_name: String,
    val clouds: String,
    val country_code: String,
    val datetime: String,
    val dewpt: String,
    val dhi: String,
    val dni: String,
    val elev_angle: String,
    val ghi: String,
    val gust: String,
    val h_angle: String,
    val lat: String,
    val lon: String,
    val ob_time: String,
    val pod: String,
    val precip: String,
    val pres: String,
    val rh: String,
    val slp: String,
    val snow: String,
    val solar_rad: String,
    val sources: List<String>,
    val state_code: String,
    val station: String,
    val sunrise: String,
    val sunset: String,
    val temp: String,
    val timezone: String,
    val ts: String,
    val uv: String,
    val vis: String,
    val weather: Weather,
    val wind_cdir: String,
    val wind_cdir_full: String,
    val wind_dir: String,
    val wind_spd: String
)

data class Weather(
    val code: String,
    val description: String,
    val icon: String
)
