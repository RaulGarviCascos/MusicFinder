package com.example.musicfinder.ui.common

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicfinder.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonTabBar(isThis: Boolean,icon_name : Int, label : String,onClick :() -> Unit,){

        Button(
            onClick = onClick,
            shape = RectangleShape,
            modifier = Modifier
                .padding(4.dp),
            colors =  ButtonDefaults.buttonColors(containerColor = Color.Transparent)
        ) {

            var colorOn = Color.White
            if ( isThis){
                colorOn = Color(red = 149, green = 122, blue = 211)
            }

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = icon_name),
                    contentDescription = "Icono personalizado",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(colorOn)
                )

                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = label,
                    color = colorOn,
                    fontSize = 12.sp
                )
                if (isThis){
                    Box(
                        modifier = Modifier
                            .width(35.dp)
                            .height(3.dp)
                            .padding(top = 1.dp)
                            .background(colorOn)
                    )
                }
            }
        }

}

@Preview(showBackground = true)
@Composable
fun PreviewButton(){
    ButtonTabBar(isThis = true, icon_name = R.drawable.ic_microphone, label = "Listen", onClick = {})
}
