package com.example.musicfinder.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicfinder.R
import com.example.musicfinder.ui.navigation.AppScreens
import com.example.musicfinder.ui.theme.MusicFinderTheme
import com.example.musicfinder.ui.theme.SpacialGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTabNavigation(isListen:Boolean,isList:Boolean,navController: NavController) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
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
@Composable
fun BottomTab(isListen:Boolean,isList:Boolean,onClickListen :()-> Unit,onClickHistorical:() -> Unit,modifier: Modifier) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ButtonTabBar(isThis = isListen, icon_name = R.drawable.ic_microphone, label = stringResource(id = R.string.bottom_bar_listen), onClick = onClickListen)
            ButtonTabBar(isThis = isList, icon_name = R.drawable.ic_list, label = stringResource(id = R.string.bottom_bar_historical), onClick =onClickHistorical)

        }
    }

}

