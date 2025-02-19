package com.example.musicfinder.ui.main
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.musicfinder.ui.common.MyTheme
import com.example.musicfinder.ui.navigation.AppNavigation
import com.example.musicfinder.ui.theme.MusicFinderTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        MyTheme.changeTheme(MyTheme.isDarkTheme().value)
        setContent {
            MusicFinderTheme {
                AppNavigation()
            }
        }
    }
}

