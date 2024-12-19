package com.example.musicfinder.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.musicfinder.ui.main.HistoricalScreen
import com.example.musicfinder.ui.main.MainScreen



@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route){
        composable( AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable( AppScreens.HistoricalScreen.route) {
            HistoricalScreen(navController)
        }
    }
}