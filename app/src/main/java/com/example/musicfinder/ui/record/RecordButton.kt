package com.example.musicfinder.ui.record

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musicfinder.R

@Composable
fun RecordButton(onClick : () -> Unit){
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors =  ButtonDefaults.buttonColors(containerColor = Color.Transparent)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_button_finder),
            contentDescription = "Icono personalizado",
            modifier = Modifier.size(200.dp)
        )

    }
}