package com.example.weatherappcompose.location

import android.Manifest
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import com.example.weatherappcompose.common.WeatherDetail
import com.example.weatherappcompose.ui.MainActivity
import com.example.weatherappcompose.ui.ResponseState
import com.example.weatherappcompose.ui.WeatherViewModel

@Composable
fun LocationService(
    viewModel: WeatherViewModel,
){
    val context = LocalContext.current
    val location = viewModel.location.value
    val weather by viewModel.currentLocationWeather.observeAsState()
    val locationUtils = remember{LocationUtils(context)}

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions(),
        onResult = { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true
                && permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ){
                locationUtils.requestLocation(viewModel)
                Toast.makeText(context, "Permission granted", Toast.LENGTH_SHORT).show()
            }

            else{
                val rationaleRequired = ActivityCompat.shouldShowRequestPermissionRationale(
                    context as MainActivity,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) || ActivityCompat.shouldShowRequestPermissionRationale(
                    context,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

                val message = if(rationaleRequired) "Location Permission is required"
                else "Location permission is required. Please enable it in android settings."
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        })

    LaunchedEffect(Unit) {
        if(!locationUtils.hasLocationPermission(context)) {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )
        }else{
            if(location == null) {
                locationUtils.requestLocation(viewModel)
            }
        }
    }

    LaunchedEffect(location){
        location?.let {
            if(weather !is ResponseState.Success){
                viewModel.getCurWeather(it)
            }
        }
    }

    when(val result = weather){
        is ResponseState.Success->{
            WeatherDetail(data = result.data)
        }

        is ResponseState.Error -> {
            Text(
                text = result.error,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(25.dp)
                    .padding(top = 30.dp)
            )
        }
        ResponseState.Loading -> {
            CircularProgressIndicator(
                color = Color.Black
            )
        }
        null, ResponseState.Empty -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (!locationUtils.hasLocationPermission(context)) {
                    Text(text = "Location permission is disabled. Enable it in your android settings to fetch weather")
                } else {

                    CircularProgressIndicator(
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    Text(text = "Trying to fetch weather", fontSize = 30.sp)
                }
            }
        }
    }
}