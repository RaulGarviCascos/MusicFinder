package com.example.musicfinder.ui.main
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
import com.example.musicfinder.R
import com.example.musicfinder.ui.common.BottomTab


@Composable
fun MainScreen() {
    val isToggled = remember { mutableStateOf(false) }
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {

        if (isToggled.value){
            FullScreenVideoBackground()
        }else{
            BackGround()
        }

        TopBar()
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {isToggled.value = !isToggled.value},
                modifier = Modifier
                    .size(200.dp),
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
            Spacer(modifier = Modifier.height(600.dp))
            Text(
                text = "Press to listen",
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
    BottomTab(isListen = true, isList = false)
}

@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    MainScreen()
}