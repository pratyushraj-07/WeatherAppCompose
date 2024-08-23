package com.example.weatherappcompose.nav_graph

sealed class Route(val route: String) {
    object HomeScreen: Route("home")
    object SearchedCity: Route("searched_city")
}