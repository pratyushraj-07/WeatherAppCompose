package com.example.weatherappcompose.screens.searchedcityscreen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherappcompose.R
import com.example.weatherappcompose.ui.ResponseState
import com.example.weatherappcompose.ui.WeatherViewModel

@Composable
fun SearchedCity(
    viewModel: WeatherViewModel
) {
    val weather by viewModel.weather.observeAsState()
    var city by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    var isVisible by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bgsunset), contentDescription = "Background",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                if (isVisible) {
                    OutlinedTextField(
                        modifier = Modifier
                            .padding(start = 16.dp, end = 16.dp)
                            .weight(1f),
                        value = city,
                        maxLines = 1,
                        onValueChange = { city = it },
                        shape = RoundedCornerShape(28.dp),
                        placeholder = { Text(text = "Search City", color = Color.Black) },
                        trailingIcon = {
                            Row {
                                IconButton(onClick = {
                                    city = ""
                                    viewModel.resetWeather()
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear",
                                        tint = Color.Black
                                    )
                                }
                                IconButton(onClick = {
                                    if (city.isNotEmpty()) {
                                        viewModel.getWeather(city)
                                        keyboardController?.hide()
                                    } else {
                                        isVisible = false
                                    }
                                }) {
                                    Icon(
                                        imageVector = Icons.Default.Search,
                                        contentDescription = "Search",
                                        tint = Color.Black
                                    )
                                }
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            focusedTextColor = Color.Black,
                            unfocusedLabelColor = Color.Black
                        )
                    )
                } else {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { isVisible = true }) {
                            Icon(
                                imageVector = Icons.Default.Search, contentDescription = "Search",
                                modifier = Modifier.size(40.dp)
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            when (val result = weather) {
                is ResponseState.Error -> {
                    Text(
                        text = result.error,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(25.dp)
                    )
                }

                ResponseState.Loading -> {
                    CircularProgressIndicator(
                        color = Color.Black
                    )
                }

                is ResponseState.Success -> {
                    WeatherDetail(result.data)
                }

                null, ResponseState.Empty -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Icon(
                            painter = painterResource(id = R.drawable.hello),
                            contentDescription = "Search ",
                            modifier = Modifier.size(80.dp)
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Search for any city",
                            fontSize = 28.sp
                        )
                    }
                }
            }
        }
    }
}