package com.example.musicfinder.ui.main
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.musicfinder.ui.common.TopBar
import com.example.musicfinder.ui.common.BackGround
import androidx.compose.runtime.mutableStateOf
import com.example.musicfinder.ui.common.BottomTab
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import com.example.musicfinder.ui.navigation.AppNavigation
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.runtime.rememberCoroutineScope
import com.example.musicfinder.ui.animations.SlideContent
import com.example.musicfinder.ui.settings.ShowLogin
import com.example.musicfinder.ui.settings.ShowSettingsMenu

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {



    val isListen = remember { mutableStateOf(true) }
    val isHistorical = remember { mutableStateOf(false) }
    val menuSettings = remember { mutableStateOf(false) }
    val menuLogin = remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = { TopBar(menuSettings,menuLogin) },
        content = { topPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(
                            onHorizontalDrag = { change, dragAmount ->
                                if (dragAmount > 15) {
                                    isListen.value = true
                                    isHistorical.value = false
                                } else if (dragAmount < -15) { 
                                    isListen.value = false
                                    isHistorical.value = true
                                }
                            }
                        )
                    }
            ) {
                BackGround()
                SlideContent(
                    visible = isListen.value,
                    topPadding = topPadding,
                    enterDirection = -1
                ) {
                    HomeBody(it,snackbarHostState,coroutineScope)
                }

                SlideContent(
                    visible = isHistorical.value,
                    topPadding = topPadding,
                    enterDirection = 1
                ) {
                    HistoricalBody(it)
                }
                ShowSettingsMenu(menuSettings,topPadding)
                ShowLogin(menuLogin,topPadding,snackbarHostState,coroutineScope)


            }
        },
        bottomBar = {
            BottomTab(
                isListen = isListen.value,
                isList = isHistorical.value,
                onClickListen = {
                    isListen.value = true
                    isHistorical.value = false
                },
                onClickHistorical = {
                    isHistorical.value = true
                    isListen.value = false
                }
            )
        }
    )
}






@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    AppNavigation()
}