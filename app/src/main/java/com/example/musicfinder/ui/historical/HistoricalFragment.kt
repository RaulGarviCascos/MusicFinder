package com.example.musicfinder.ui.main
import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.musicfinder.ui.historical.CardSong
import com.example.musicfinder.ui.navigation.AppNavigation
import com.example.musicfinder.ui.navigation.AppScreens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HistoricalScreen(navController:NavController ){
    val currentTab = remember { mutableStateOf(AppScreens.HistoricalScreen.route) } // Estado para la pestaña activa
    Scaffold {
        HistoricalBody(navController)
        BottomTab(
            isListen = currentTab.value == AppScreens.MainScreen.route,
            isList = currentTab.value == AppScreens.HistoricalScreen.route,
            navController = navController
        )

    }
}
@Composable
fun HistoricalBody(navController:NavController ) {

    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        BackGround()
        TopBar()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(40.dp))

        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            val context = LocalContext.current
            val repository = SongRepository(context)
            val savedSongs = repository.getSongs(key="Songs")
            Log.d("Songs", "Songs: $savedSongs")

            CardSong(savedSongs[0],R.drawable.ic_placeholder_image)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHistorical(){
    AppNavigation()
}