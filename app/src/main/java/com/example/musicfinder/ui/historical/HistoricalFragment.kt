package com.example.musicfinder.ui.main
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.musicfinder.R
import com.example.musicfinder.ui.common.TopBar
import com.example.musicfinder.ui.common.BackGround
import com.example.musicfinder.data.model.Song
import com.example.musicfinder.data.repository.SongRepository
import com.example.musicfinder.ui.common.BottomTab
import com.example.musicfinder.ui.historical.ListCardSong
import com.example.musicfinder.ui.navigation.AppNavigation
import com.example.musicfinder.ui.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoricalScreen(navController:NavController ){
    val currentTab = remember { mutableStateOf(AppScreens.HistoricalScreen.route) }
    Scaffold(
        topBar = { TopBar() },
        content = {topPadding ->
            HistoricalBody(topPadding)
        },
        bottomBar = {
            BottomTab(
                isListen = currentTab.value == AppScreens.MainScreen.route,
                isList = currentTab.value == AppScreens.HistoricalScreen.route,
                navController = navController)
            }
        )

}
@Composable
fun HistoricalBody(padding : PaddingValues) {
    BackGround()
    Box(
        modifier = Modifier.fillMaxWidth().padding(padding)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            val context = LocalContext.current
            val repository = SongRepository(context)
            val savedSongs = repository.getSongs(key="Songs")
            Log.d("Songs", "Songs: $savedSongs")


            Column() {
                savedSongs.forEach { song ->
                    ListCardSong(song)
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHistorical(){
    AppNavigation()
}