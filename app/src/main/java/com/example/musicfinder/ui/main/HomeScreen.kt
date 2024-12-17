package com.example.musicfinder.ui.main
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.example.musicfinder.R

import com.example.musicfinder.ui.common.TopBar
import com.example.musicfinder.ui.common.BackGround
import com.example.musicfinder.ui.common.FullScreenVideoBackground

@Composable
fun MainScreen(onRecognizeClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        FullScreenVideoBackground()
        //BackGround()
        TopBar()

        // Imagen de fondo

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            // Botón de Reconocimiento
            Button(
                onClick = onRecognizeClick,
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Black, shape = CircleShape),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray),
            ) {
                Text(
                    text = "🎤",
                    fontSize = 40.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Press to listen",
                fontSize = 16.sp,
                color = Color.Black,
                style =  MaterialTheme.typography.titleLarge

            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewScreen(){
    MainScreen(onRecognizeClick = {})
}