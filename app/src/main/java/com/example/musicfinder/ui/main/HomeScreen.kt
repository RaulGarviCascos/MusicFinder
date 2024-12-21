package com.example.musicfinder.ui.main
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt
import androidx.compose.runtime.LaunchedEffect

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val isListen = remember { mutableStateOf(true) }
    val isHistorical = remember { mutableStateOf(false) }

    Scaffold(
        topBar = { TopBar() },
        content = { topPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(Unit) {
                        detectHorizontalDragGestures(

                            onHorizontalDrag = { change, dragAmount ->
                                if (dragAmount > 15) { // Desliza a la derecha
                                    isListen.value = true
                                    isHistorical.value = false
                                } else if (dragAmount < -15) { // Desliza a la izquierda
                                    isListen.value = false
                                    isHistorical.value = true
                                }
                            }
                        )
                    }
            ) {
                BackGround()
                AnimatedContent(
                    visible = isListen.value,
                    topPadding = topPadding,
                    enterDirection = -1
                ) {
                    MainBody(it)
                }

                AnimatedContent(
                    visible = isHistorical.value,
                    topPadding = topPadding,
                    enterDirection = 1
                ) {
                    HistoricalBody(it)
                }
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
@Composable
fun AnimatedContent(visible:Boolean,topPadding: PaddingValues, enterDirection: Int, content: @Composable (PaddingValues) -> Unit){
    AnimatedVisibility(
        visible = visible,
        enter =
        slideInHorizontally(animationSpec = tween(durationMillis = 200)) { fullWidth ->
            fullWidth / 3*enterDirection
        } +
                fadeIn(
                    // Overwrites the default animation with tween
                    animationSpec = tween(durationMillis = 200)
                ),
        exit =
        slideOutHorizontally(animationSpec = spring(stiffness = Spring.StiffnessHigh)) {
            200*enterDirection
        } + fadeOut()
    ) {
       content(topPadding)
    }
}


@Composable
fun ButtonListen(onClick :() -> Unit, listenText:String){
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onClick,
            shape = CircleShape,
            colors =  ButtonDefaults.buttonColors(containerColor = Color.Transparent), // Fondo transparente

        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_button_finder), // Tu imagen personalizada
                contentDescription = "Icono personalizado",
                modifier = Modifier.size(200.dp)
            )
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(500.dp))
        Text(
            text = listenText,
            style = TextStyle(fontSize = 20.sp,
                color = Color.Cyan,
                fontWeight =  FontWeight.Bold,
                shadow = Shadow(
                    color = Color.Black,
                    offset = Offset(-4f, 4f),
                    blurRadius = 2f
                ))
        )
    }
}

@Composable
fun MainBody(topPadding : PaddingValues) {
    val isToggled = remember { mutableStateOf(false) }

    var listenText = ""
    if (isToggled.value){
        //FullScreenVideoBackground()
        listenText = "Listening..."
    }else{
        //BackGround()
        listenText = "Press to listen"
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(topPadding),
    ) {
        ButtonListen(onClick = {isToggled.value = !isToggled.value},listenText)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    AppNavigation()
}