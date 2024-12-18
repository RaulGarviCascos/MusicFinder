package com.example.musicfinder.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.musicfinder.R

@Composable
fun BackGround(){
    Image(
        painter = painterResource(id = R.drawable.captura_background), // Reemplaza con tu imagen
        contentDescription = "Background Image",
        contentScale = ContentScale.Crop, // Para que ocupe toda la pantalla
        modifier = Modifier.fillMaxSize()
    )
}