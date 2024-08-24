package com.example.weatherappcompose.common

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.weatherappcompose.R

@Composable
fun Lottie() {

    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.animation))

    LottieAnimation(
        composition = composition,
        modifier = Modifier.size(250.dp),
        iterations = LottieConstants.IterateForever
    )

}

