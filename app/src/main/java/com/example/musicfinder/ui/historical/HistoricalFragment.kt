package com.example.musicfinder.ui.main
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicfinder.ui.common.TopBar
import com.example.musicfinder.ui.common.BackGround
import com.example.musicfinder.data.model.Song
import com.example.musicfinder.ui.common.BottomTab
import com.example.musicfinder.ui.historical.CardSong


@Composable
fun HistoricalScreen() {
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
            CardSong(song)
        }
    }
    BottomTab(isListen = false, isList = true)
}

@Preview(showBackground = true)
@Composable
fun PreviewHistorical(){
    HistoricalScreen()
}