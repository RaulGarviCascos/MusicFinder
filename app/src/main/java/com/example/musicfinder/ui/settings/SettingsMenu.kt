package com.example.musicfinder.ui.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.musicfinder.data.model.AudDResponseModels.SongResult
import com.example.musicfinder.ui.animations.CardAnimation
import com.example.musicfinder.ui.animations.SlideContent
import com.example.musicfinder.ui.common.MyTheme


@Composable
fun SettingsMenu( padding:PaddingValues,onClick : () -> Unit) {
    val isDarkThemeBoolean = MyTheme.isDarkTheme().collectAsState().value
    val checked = remember { mutableStateOf(isDarkThemeBoolean) }

    Box(
        modifier = Modifier.width(150.dp).height(300.dp).background(
            MaterialTheme.colorScheme.background
            , shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 0.dp)
        ).padding(padding)
    ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 30.dp, top = 10.dp)
            ) {

                Text("Dark theme", modifier = Modifier.padding(5.dp))
                Switch(
                    checked = checked.value,
                    onCheckedChange = {
                        checked.value = !checked.value
                        MyTheme.changeTheme(checked.value)
                    },
                    modifier = Modifier.padding(5.dp)
                )
            }
        }

}
@Composable
fun ShowSettingsMenu(isVisible:MutableState<Boolean>,padding:PaddingValues,onClick : () -> Unit){
    //para que cuando pulse fuera del menu, se quite
    if(isVisible.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.8f))
                .clickable { isVisible.value = !isVisible.value }
        ) {

        }
    }
    SlideContent(visible = isVisible.value, topPadding=padding, enterDirection = -1){
        SettingsMenu(padding,onClick = onClick)
    }


}


//@Preview(showBackground = true)
//@Composable
//fun PreviewScreen(){
//    val checked = remember { mutableStateOf(true) }
//
//    SettingsMenu(checked, onClick = {})
//}