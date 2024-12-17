package com.example.musicfinder.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    // TopAppBar con fondo gris
    Box(
        modifier = Modifier
            .fillMaxWidth() // Asegura que la barra ocupa todo el ancho
            .background(Color(0xFFE0E0E0)) // Fondo gris
    ) {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(
                    red = 55,
                    green = 53,
                    blue = 66
                )
            ),
            title = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center // Centra el título en la barra
                ) {
                    Text(
                        text = "Music Finder",
                        fontSize = 20.sp,
                        color = Color.White,
                        style  = MaterialTheme.typography.titleLarge
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = { /* Acción del icono de menú */ }) {
                    Icon(
                        imageVector = Icons.Filled.Menu,
                        contentDescription = "Menu Icon",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = { /* Acción del avatar */ }) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "Avatar Icon",
                        tint = Color.White
                    )
                }
            }, modifier = Modifier.fillMaxWidth() // Explicita el ancho completo
        )
    }
}

