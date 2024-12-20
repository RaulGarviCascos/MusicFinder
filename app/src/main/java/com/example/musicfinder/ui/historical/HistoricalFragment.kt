package com.example.musicfinder.ui.main
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.musicfinder.ui.common.BottomTabNavigation
import com.example.musicfinder.ui.historical.ListCardSong
import com.example.musicfinder.ui.navigation.AppNavigation
import com.example.musicfinder.ui.navigation.AppScreens

//ya no lo uso
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
            BottomTabNavigation(
                isListen = currentTab.value == AppScreens.MainScreen.route,
                isList = currentTab.value == AppScreens.HistoricalScreen.route,
                navController = navController)
            }
        )

}
@Composable
fun HistoricalBody(padding : PaddingValues) {

    Box(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        val context = LocalContext.current
        val repository = SongRepository(context)
//        val song = Song( title = "Warriors",artist="Imagine Dragons",album="Warriors",url_image="https://i.scdn.co/image/b039549954758689330893bd4a92585092a81cf5",url_spotify="https://open.spotify.com/artist/53XhwfbYqKCa1cC15pYq2q")
//
//        val songs = listOf(song,song,song,song,song,song,song,song,song,song,song,song,song,song,song,song,song,song,song,song,song)
//        repository.saveSongs(key="Songs",songs)
        val savedSongs = repository.getSongs(key="Songs")
        Log.d("Songs", "Songs: $savedSongs")


        LazyColumn() {
            items(savedSongs) { song ->
                ListCardSong(song)
            }

        }

    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHistorical(){
    AppNavigation()
}