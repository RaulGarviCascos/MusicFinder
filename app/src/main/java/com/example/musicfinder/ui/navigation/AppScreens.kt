package com.example.musicfinder.ui.navigation

sealed class AppScreens(val route: String) {
    object MainScreen : AppScreens("main")
    object HistoricalScreen : AppScreens("screen")
}