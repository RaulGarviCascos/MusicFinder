package com.example.musicfinder.ui.main
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.musicfinder.ui.historical.DetailedCard
import com.example.musicfinder.ui.historical.ListCardSong
import com.example.musicfinder.ui.navigation.AppNavigation
import com.example.musicfinder.ui.navigation.AppScreens

//ya no lo uso
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoricalScreen(){
//    val currentTab = remember { mutableStateOf(AppScreens.HistoricalScreen.route) }
//    Scaffold(
//        topBar = { TopBar() },
//        content = {topPadding ->
//            HistoricalBody(topPadding)
//        },
//        bottomBar = {
//            BottomTabNavigation(
//                isListen = currentTab.value == AppScreens.MainScreen.route,
//                isList = currentTab.value == AppScreens.HistoricalScreen.route,
//                navController = navController)
//            }
//        )

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
        val emptySong = Song()
        var selectedSong = remember { mutableStateOf<Song?>(null) } // Estado para la canción seleccionada
        var isVisible = remember { mutableStateOf(false) }
        LazyColumn() {
            items(savedSongs) { song ->
                Box(
                    modifier = Modifier.fillMaxSize().clickable {
                        selectedSong.value = song
                        isVisible.value=true
                    },


                ) {
                    ListCardSong(song)
                }
            }
        }
        if(isVisible.value){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .clickable { isVisible.value = !isVisible.value } // Cierra el detalle al hacer clic fuera
            ) {

            }
        }
        AnimatedVisibility(
            visible = isVisible.value,
            enter = scaleIn(animationSpec = tween(durationMillis = 500)),
            exit = scaleOut(animationSpec = tween(durationMillis = 500))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DetailedCard(song = selectedSong.value?:emptySong)
            }
        }



    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHistorical(){
    AppNavigation()
}