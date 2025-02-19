package com.example.musicfinder.ui.settings

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.musicfinder.R
import com.example.musicfinder.ui.animations.SlideContent
import com.example.musicfinder.ui.common.MyTheme
import com.example.musicfinder.ui.common.setAppLocale


@Composable
fun SettingsMenu( padding:PaddingValues) {
    val isDarkThemeBoolean = MyTheme.isDarkTheme().collectAsState().value
    val isDark = remember { mutableStateOf(isDarkThemeBoolean) }
    val context = LocalContext.current
    val activity = context as? Activity
    val lenguage = context.resources.configuration.locales[0].language
    val isSpanish = remember{ mutableStateOf(lenguage=="es") }
    Card(
        modifier = Modifier.width(300.dp).height(400.dp).padding(top = padding.calculateTopPadding()),
        shape = RoundedCornerShape(0.dp, 0.dp, 10.dp, 0.dp)
    ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(start = 30.dp, top = 20.dp)
            ) {

                Text(stringResource(id = R.string.swap_theme), modifier = Modifier.padding(5.dp),fontWeight = FontWeight.Bold)
                Switch(
                    checked = isDark.value,
                    onCheckedChange = {
                        isDark.value = !isDark.value
                        MyTheme.changeTheme(isDark.value)
                    },
                    modifier = Modifier.padding(5.dp)
                )

                Divider(
                    color = Color.Gray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(end = 30.dp, top = 5.dp, bottom = 5.dp)
                )
                Text(stringResource(id = R.string.change_language), modifier = Modifier.padding(5.dp),fontWeight = FontWeight.Bold)
                Row (modifier = Modifier.fillMaxWidth().padding(5.dp), verticalAlignment = Alignment.CenterVertically){
                    Text(stringResource(id = R.string.english_language), modifier = Modifier.padding(5.dp), textAlign = TextAlign.Center)
                    Switch(
                        checked = isSpanish.value,
                        onCheckedChange = {
                            isSpanish.value = !isSpanish.value
                            setAppLocale(context,if(isSpanish.value) "es" else "en")
                            activity?.let { activity.recreate() }
                        },
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(stringResource(id = R.string.spanish_language), modifier = Modifier.padding(5.dp))
                }

            }
        }

}
@Composable
fun ShowSettingsMenu(isVisible:MutableState<Boolean>,padding:PaddingValues){

    if(isVisible.value){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .clickable { isVisible.value = !isVisible.value }
        ) {

        }
    }

    SlideContent(visible = isVisible.value, topPadding=padding, enterDirection = -1){
        SettingsMenu(padding)
    }


}

