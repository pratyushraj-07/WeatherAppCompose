package com.example.weatherappcompose.nav_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherappcompose.screens.HomeScreen
import com.example.weatherappcompose.screens.SearchedCity
import com.example.weatherappcompose.ui.WeatherViewModel

@Composable
fun Navigation(
    viewModel: WeatherViewModel,
    navController: NavHostController = rememberNavController()
){
    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {

        composable(
            route = Route.HomeScreen.route,
            enterTransition = { slideInHorizontally() },
            exitTransition = { slideOutHorizontally(animationSpec = tween(500)) }
        ){
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(
            route =  Route.SearchedCity.route,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(350)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(400)
                )
            }
        ){
            SearchedCity(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}