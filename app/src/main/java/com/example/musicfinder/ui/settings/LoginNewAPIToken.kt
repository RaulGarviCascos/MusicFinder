
package com.example.musicfinder.ui.settings

import android.app.Activity
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicfinder.R
import com.example.musicfinder.data.model.AudDResponseModels.SongResult
import com.example.musicfinder.ui.animations.CardAnimation
import com.example.musicfinder.ui.animations.SlideContent
import com.example.musicfinder.ui.common.MyTheme
import com.example.musicfinder.ui.common.setAppLocale


@Composable
fun LoginNewAPIToken( padding:PaddingValues) {
    val isDarkThemeBoolean = MyTheme.isDarkTheme().collectAsState().value
    val isDark = remember { mutableStateOf(isDarkThemeBoolean) }
    val context = LocalContext.current
    val activity = context as? Activity
    val lenguage = context.resources.configuration.locales[0].language
    val isSpanish = remember{ mutableStateOf(lenguage=="es") }
    var password = rememberSaveable { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd){
        Card(
            modifier = Modifier.fillMaxWidth().height(300.dp).padding(top = padding.calculateTopPadding()),
            shape = RoundedCornerShape(0.dp, 0.dp, 0.dp, 10.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 30.dp, top = 20.dp)
            ) {

                TextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    label = { Text(stringResource(id = R.string.login_api), modifier = Modifier.padding(5.dp),fontWeight = FontWeight.Bold) },
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )


            }
        }
    }
}

@Composable
fun ShowLogin(isVisible:MutableState<Boolean>,padding:PaddingValues){

    if(isVisible.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .clickable { isVisible.value = !isVisible.value }
        ) {

        }
    }

    SlideContent(visible = isVisible.value, topPadding=padding, enterDirection =1){
        LoginNewAPIToken(padding)
    }


}


@Preview(showBackground = true)
@Composable
fun PreviewLogin(){
    val padding = PaddingValues(50.dp)
    LoginNewAPIToken(padding)
}