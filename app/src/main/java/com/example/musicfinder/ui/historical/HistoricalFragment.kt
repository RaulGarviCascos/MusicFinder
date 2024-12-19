package com.example.musicfinder.ui.main
import android.annotation.SuppressLint
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.musicfinder.R
import com.example.musicfinder.ui.common.TopBar
import com.example.musicfinder.ui.common.BackGround
import com.example.musicfinder.data.model.Song
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
            val song = Song( title = "Warriors",artist="Imagine Dragons",album="Warriors",url_image="https://i.scdn.co/image/b039549954758689330893bd4a92585092a81cf5",url_spotify="https://open.spotify.com/artist/53XhwfbYqKCa1cC15pYq2q")
            CardSong(song,R.drawable.ic_placeholder_image)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewHistorical(){
    AppNavigation()
}