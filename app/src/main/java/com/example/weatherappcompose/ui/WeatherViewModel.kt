package com.example.weatherappcompose.ui

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.Constant
import com.example.weatherappcompose.data.RetrofitInstance
import com.example.weatherappcompose.data.WeatherApi
import com.example.weatherappcompose.data.WeatherApi2
import com.example.weatherappcompose.data.models.WeatherModel
import com.example.weatherappcompose.location.LocationData
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherApi: WeatherApi = RetrofitInstance.api,
    private val weatherApi2: WeatherApi2 = RetrofitInstance.api2
) : ViewModel() {

    private val _weather = MutableLiveData<ResponseState<WeatherModel>>()
    val weather : LiveData<ResponseState<WeatherModel>> = _weather

    private val _location = mutableStateOf<LocationData?>(null)
    val location : State<LocationData?> = _location

    fun getWeather(city: String){
        _weather.value = ResponseState.Loading
        viewModelScope.launch {
            try {
                val result = weatherApi.getWeather(city, Constant.APIKEY)
                if (result.isSuccessful) {
                    Log.i("WeatherVM", "${result.body()}")
                    result.body()?.let {
                        _weather.value = ResponseState.Success(it)
                    }
                } else {
                    _weather.value = ResponseState.Error("Error: ${result.message()}")
                }
            }catch (e:Exception){
                _weather.value = ResponseState.Error("Error : ${e.message}")
            }
        }
    }

    fun getCurWeather(location: LocationData){
        _weather.value = ResponseState.Loading
        viewModelScope.launch {
            try {
                val result = weatherApi2.getCurWeather(latitude = location.latitude, longitude = location.longitude, apikey = Constant.APIKEY)
                if(result.isSuccessful){
                    result.body()?.let {
                        _weather.value = ResponseState.Success(it)
                    }
                }else{
                    _weather.value = ResponseState.Error("Error : ${result.message()}")
                }
            }catch (e:Exception){
                _weather.value = ResponseState.Error("Error : ${e.message}")
            }
        }
    }

    fun resetWeather(){
        _weather.value = ResponseState.Empty
    }

    fun getLocation(location:LocationData){
        _location.value = location
    }

}