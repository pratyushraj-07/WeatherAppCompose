package com.example.weatherappcompose.common

import androidx.annotation.DrawableRes
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultCameraDistance
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.weatherappcompose.R
import com.example.weatherappcompose.data.models.WeatherModel

@Composable
fun WeatherDetail(
    data: WeatherModel
) {
    if (data.data.isNotEmpty()) {
        val weather = data.data.first()
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            colors = CardDefaults.cardColors(Color.Transparent)
        ) {
            Column(
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        tint = Color.DarkGray,
                        modifier = Modifier.size(32.dp)
                    )

                    Text(
                        text = weather.city_name + ", ",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = weather.country_code,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Text(
                text = "Time zone: ${weather.timezone}",
                modifier = Modifier.padding(start = 35.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        verticalArrangement = Arrangement.Top,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            modifier = Modifier.padding(top = 12.dp),
                            text = weather.temp + " °c",
                            fontSize = 43.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Air quality: ${weather.aqi}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = "Wind speed: ${weather.wind_spd}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Column(
                        modifier = Modifier.padding(top = 8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        WeatherIcon(iconCode = weather.weather.icon,
                            modifier = Modifier.size(70.dp))
                        Text(
                            text = weather.weather.description,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(80.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 6.dp, end = 6.dp),
                colors = CardDefaults.cardColors(colorResource(id = R.color.Aquamarine))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherCard(key = weather.rh, name = "Humidity", icon = R.drawable.humidity)
                    WeatherCard(key = weather.pres, name = "Atm Pres", icon = R.drawable.pressure)
                    WeatherCard(key = weather.precip, name = "Precip", icon = R.drawable.ice)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherCard(key = weather.sunset, name = "Sunset", icon = R.drawable.sunset)
                    WeatherCard(key = weather.sunrise, name = "Sunrise", icon = R.drawable.sunrise)
                    WeatherCard(key = weather.uv, name = "UV", icon = R.drawable.rays)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    WeatherCard(
                        key = weather.vis,
                        name = "Visibility",
                        icon = R.drawable.visibility
                    )
                    WeatherCard(
                        key = weather.app_temp,
                        name = "Feels like",
                        icon = R.drawable.feelsliketemp
                    )
                    WeatherCard(key = weather.clouds, name = "Cloud ", icon = R.drawable.cloud)
                }
                Spacer(modifier = Modifier.height(8.dp))

                HorizontalDivider(
                    color = Color.DarkGray,
                    thickness = 2.dp
                )
            }
        }
    }
}

@Composable
fun WeatherCard(
    key: String,
    name: String,
    @DrawableRes icon: Int
) {
    Card(
        modifier = Modifier.size(90.dp),
        colors = CardDefaults.cardColors(colorResource(id = R.color.Aquamarine)),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier
                    .size(33.dp)
                    .padding(end = 5.dp),
                painter = painterResource(id = icon),
                contentDescription = "Icon"
            )
            Text(
                text = key,
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = name,
                fontSize = 12.sp,
                modifier = Modifier.padding(end = 8.dp),
                fontWeight = FontWeight.Bold
            )
        }
        HorizontalDivider(
            color = Color.DarkGray,
            modifier = Modifier.padding(top = 2.dp),
            thickness = 7.dp
        )
    }
}

@Composable
fun WeatherIcon(iconCode: String, modifier: Modifier = Modifier) {
    val iconUrl = "https://www.weatherbit.io/static/img/icons/$iconCode.png"
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(iconUrl)
            .placeholder(R.drawable.images)
            .build(),
        contentDescription = "Weather Icon",
        modifier = modifier.size(60.dp),
    )
}