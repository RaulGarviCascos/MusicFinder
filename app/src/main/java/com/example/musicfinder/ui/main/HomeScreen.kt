package com.example.musicfinder.ui.main
import android.annotation.SuppressLint
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
import com.example.musicfinder.ui.common.FullScreenVideoBackground
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicfinder.R
import com.example.musicfinder.ui.common.BottomTab
import androidx.navigation.compose.composable
import androidx.navigation.compose.NavHost
import com.example.musicfinder.ui.common.BottomTab
import com.example.musicfinder.ui.navigation.AppNavigation
import com.example.musicfinder.ui.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController:NavController ){

    val isListen = remember { mutableStateOf(true) }
    val isHistorical = remember { mutableStateOf(false) }
    Scaffold(
        topBar = { TopBar() },
        content = {topPadding ->
            if(isListen.value) MainBody(topPadding)
            if(isHistorical.value) HistoricalBody(topPadding)
        },
        bottomBar = {

            BottomTab(isListen = isListen.value,isList = isHistorical.value,
                onClickListen = {isListen.value = !isListen.value
                    isHistorical.value = !isHistorical.value},
                onClickHistorical = {isListen.value = !isListen.value
                    isHistorical.value = !isHistorical.value},
                )
        }
    )

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
        FullScreenVideoBackground()
        listenText = "Listening..."
    }else{
        BackGround()
        listenText = "Press to listen"
    }
    Box(
        modifier = Modifier.fillMaxSize().padding(topPadding),
    ) {
        ButtonListen(onClick = {isToggled.value = !isToggled.value},listenText)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    AppNavigation()
}