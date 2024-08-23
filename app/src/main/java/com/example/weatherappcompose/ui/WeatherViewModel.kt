package com.example.weatherappcompose.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherappcompose.Constant
import com.example.weatherappcompose.data.RetrofitInstance
import com.example.weatherappcompose.data.WeatherApi
import com.example.weatherappcompose.data.WeatherModel
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val weatherApi: WeatherApi = RetrofitInstance.api
) : ViewModel() {

    private val _weather = MutableLiveData<ResponseState<WeatherModel>>()
    val weather : LiveData<ResponseState<WeatherModel>> = _weather

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
                     Log.e("WeatherViewModel", "Error: ${result.message()}")
                    _weather.value = ResponseState.Error("Error: ${result.message()}")
                }
            }catch (e:Exception){
                 Log.e("WeatherViewModel", "Error: ${e.message}", e)
                _weather.value = ResponseState.Error("Error : ${e.message}")
            }
        }
    }

    fun resetWeather(){
        _weather.value = ResponseState.Empty
    }

}