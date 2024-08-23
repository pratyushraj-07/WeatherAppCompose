package com.example.weatherappcompose.ui

sealed class ResponseState<out T> {
    data class Success<out T>(val data : T): ResponseState<T>()
    data class Error<T>(val error: String) : ResponseState<T>()
    data object Loading : ResponseState<Nothing>()
    data object Empty: ResponseState<Nothing>()
}