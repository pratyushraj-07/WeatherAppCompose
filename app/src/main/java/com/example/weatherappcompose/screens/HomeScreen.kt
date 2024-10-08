package com.example.weatherappcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.weatherappcompose.R
import com.example.weatherappcompose.location.LocationService
import com.example.weatherappcompose.nav_graph.Route
import com.example.weatherappcompose.ui.WeatherViewModel

@Composable
fun HomeScreen(
    viewModel: WeatherViewModel,
    navController: NavController
){
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.bgsunset),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentSize(),
                onClick = { navController.navigate(Route.SearchedCity.route) },
                colors = CardDefaults.cardColors(Color.Transparent)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Search ")
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.Black,
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
            }

            LocationService(viewModel = viewModel)

        }
    }
}