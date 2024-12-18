package com.example.musicfinder.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicfinder.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomTab(isListen:Boolean , isList:Boolean) {
    var isListen = remember { mutableStateOf(isListen) }
    var isList = remember { mutableStateOf(isList) }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(red = 55, green = 53, blue = 66))
                .align(Alignment.BottomCenter)

        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ButtonTabBar(isThis = isListen.value, icon_name = R.drawable.ic_microphone, label = "Listen", onClick = {
                    isList.value = false
                    isListen.value = true
                })
                ButtonTabBar(isThis = isList.value, icon_name = R.drawable.ic_list, label = "Historical", onClick = {
                    isList.value = true
                    isListen.value = false
                })

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTab(){
    BottomTab(isListen = true,isList = false)
}

