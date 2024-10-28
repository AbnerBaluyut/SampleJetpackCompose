package com.example.sample_app.core.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun screenWidth(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenWidthDp
}

@Composable
fun screenHeight(): Int {
    val configuration = LocalConfiguration.current
    return configuration.screenHeightDp
}

@Composable
fun smallestScreenDimension(): Int {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp
    val screenWidth = configuration.screenWidthDp
    return minOf(screenWidth, screenHeight)
}

@Composable
fun isPortrait() : Boolean {
    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == android.content.res.Configuration.ORIENTATION_PORTRAIT
    return isPortrait
}