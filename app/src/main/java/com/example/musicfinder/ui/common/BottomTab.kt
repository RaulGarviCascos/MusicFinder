package com.example.musicfinder.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicfinder.R
import com.example.musicfinder.ui.navigation.AppScreens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTab(isListen:Boolean , isList:Boolean,navController: NavController) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(Color(red = 55, green = 53, blue = 66))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ButtonTabBar(isThis = isListen, icon_name = R.drawable.ic_microphone, label = "Listen", onClick = {
                    navController.navigate(AppScreens.MainScreen.route)
                })
                ButtonTabBar(isThis = isList, icon_name = R.drawable.ic_list, label = "Historical", onClick = {
                    navController.navigate(AppScreens.HistoricalScreen.route)
                })

            }
        }

}

@Preview(showBackground = true)
@Composable
fun PreviewTab(){
   // BottomTab(isListen = true,isList = false, navController = NavHostController("historical"))
}

