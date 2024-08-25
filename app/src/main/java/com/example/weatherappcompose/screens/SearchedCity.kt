package com.example.weatherappcompose.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.weatherappcompose.R
import com.example.weatherappcompose.common.Lottie
import com.example.weatherappcompose.common.WeatherDetail
import com.example.weatherappcompose.ui.ResponseState
import com.example.weatherappcompose.ui.WeatherViewModel

@Composable
fun SearchedCity(
    navController: NavController,
    viewModel: WeatherViewModel
) {
    val weather by viewModel.searchedCityWeather.observeAsState()
    var city by remember { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    DisposableEffect(Unit) {
        onDispose {
            viewModel.resetWeather()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.bgsunset),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop)

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
                IconButton(onClick = { navController.navigateUp() }){
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }

                OutlinedTextField(
                    modifier = Modifier
                        .padding(start = 16.dp, end = 16.dp)
                        .weight(1f)
                        .focusRequester(focusRequester),
                    value = city,
                    maxLines = 1,
                    onValueChange = { city = it },
                    shape = RoundedCornerShape(28.dp),
                    placeholder = { Text(text = "Search City", color = Color.Black, fontWeight = FontWeight.SemiBold) },
                    trailingIcon = {
                        Row {
                            IconButton(onClick = { city = "" }) {
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
                        unfocusedLabelColor = Color.Black,
                        focusedContainerColor = colorResource(id = R.color.Aquamarine)
                    )
                )
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
                    Lottie()
                }
            }
        }
    }
}