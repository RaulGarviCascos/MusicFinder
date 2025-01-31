package com.example.musicfinder.ui.main
import SettingsAnimation
import android.Manifest
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicfinder.ui.common.TopBar
import com.example.musicfinder.ui.common.BackGround
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.musicfinder.R
import com.example.musicfinder.ui.common.BottomTab
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import com.example.musicfinder.ui.navigation.AppNavigation
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import com.example.musicfinder.data.model.AudDResponseModels.SongResult
import com.example.musicfinder.ui.animations.SlideContent
import com.example.musicfinder.ui.common.MyTheme
import com.example.musicfinder.ui.historical.DetailedCardSong
import com.example.musicfinder.ui.record.RecordAudio
import com.example.musicfinder.ui.record.RecordAudioWrapper
import com.example.musicfinder.ui.record.RecordButton
import com.example.musicfinder.ui.settings.SettingsMenu
import com.example.musicfinder.ui.settings.ShowSettingsMenu
import kotlinx.coroutines.delay

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {



    val isListen = remember { mutableStateOf(true) }
    val isHistorical = remember { mutableStateOf(false) }
    val menuSettings = remember { mutableStateOf(false) }

    Scaffold(

                topBar = { TopBar(menuSettings) },
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
                    HomeBody(it)
                }

                SlideContent(
                    visible = isHistorical.value,
                    topPadding = topPadding,
                    enterDirection = 1
                ) {
                    HistoricalBody(it)
                }
                ShowSettingsMenu(menuSettings,topPadding,onClick = {menuSettings.value = !menuSettings.value})



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