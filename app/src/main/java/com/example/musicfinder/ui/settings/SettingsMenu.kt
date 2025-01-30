package com.example.musicfinder.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicfinder.R


@Composable
fun SettingsMenu(checked: MutableState<Boolean>, padding:PaddingValues,onClick : () -> Unit) {
    Box(
        modifier = Modifier.width(150.dp).height(300.dp).background(
            color = Color(
                red = 55,
                green = 53,
                blue = 66
            ), shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 0.dp)
        ).padding(padding)
    ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 30.dp, top = 10.dp)
            ) {

                Text("Dark theme", modifier = Modifier.padding(5.dp), color = Color.White)
                Switch(
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = !checked.value
                    },
                    modifier = Modifier.padding(5.dp)
                )
            }
        }



}


//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen(){
//    val checked = remember { mutableStateOf(true) }
//
//    SettingsMenu(checked, onClick = {})
//}