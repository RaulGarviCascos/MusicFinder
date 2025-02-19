package com.example.musicfinder.ui.historical
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicfinder.R
import android.net.Uri
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Divider
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.example.musicfinder.data.model.AudDResponseModels.Album
import com.example.musicfinder.data.model.AudDResponseModels.ExternalUrlsXXX
import com.example.musicfinder.data.model.AudDResponseModels.Image
import com.example.musicfinder.data.model.AudDResponseModels.SongResult
import com.example.musicfinder.data.model.AudDResponseModels.Spotify
import com.example.musicfinder.ui.animations.CardAnimation


class DetailedCardSong {
    var song:SongResult? = null

    @Composable
    fun CreateDetailedCard() {
        val song = this.song!!
        val context = LocalContext.current
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 6.dp
            ),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
            modifier = Modifier
                .width(300.dp)
                .defaultMinSize(minHeight = 400.dp)
                .padding(5.dp)
                .wrapContentHeight(unbounded = true)
        ) {

            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ){
                ImageFromUrlSafe(song.spotify?.album?.images?.get(0)?.url,200)
                Column(

                ){
                    Text(
                        text = ""+song.title,
                        modifier = Modifier
                            .padding(start = 20.dp,5.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                    val textCard = listOf(stringResource(id = R.string.card_detail_artist), stringResource(id = R.string.card_detail_album), stringResource(id = R.string.card_detail_release_date))
                    val textSong = listOf(song.artist,song.album,song.release_date)
                    for (i in textCard.indices){
                        Divider(
                            color = Color.Gray,
                            thickness = 1.dp,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 5.dp)
                        )
                        Text(
                            text =textCard[i]+" "+textSong[i],
                            modifier = Modifier
                                .padding(start = 20.dp,5.dp),
                            textAlign = TextAlign.Left,
                            fontSize = 18.sp
                        )
                    }

                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Button(onClick = {
                            val uri = Uri.parse(song.spotify?.external_urls?.spotify)
                            try {
                                val spotifyIntent = Intent(Intent.ACTION_VIEW, uri).apply {
                                    `package` = "com.spotify.music"
                                }
                                context.startActivity(spotifyIntent)
                            } catch (e: Exception) {
                                if (isSpotifyInstalled(context)){
                                    val browserIntent = Intent(Intent.ACTION_VIEW, uri)
                                    context.startActivity(browserIntent)
                                }
                                e.printStackTrace()
                                Toast.makeText(context, "Ocurrió un error.", Toast.LENGTH_SHORT).show()
                            }
                        },
                            shape = CircleShape,
                            colors =  ButtonDefaults.buttonColors(containerColor = Color.Transparent)) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_spotify),
                                contentDescription = "Spotify",
                                alignment =  Alignment.Center
                            )
                        }

                    }
                }

            }
        }

    }
    fun isSpotifyInstalled(context: Context): Boolean {
        val packageManager = context.packageManager
        return try {
            packageManager.getPackageInfo("com.spotify.music", 0)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    @Composable
    fun showDetailCard(song:SongResult,isVisible: MutableState<Boolean>){
        this.song=song
        if(isVisible.value){
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.8f))
                    .clickable { isVisible.value = !isVisible.value }
            ) {

            }

        }
        CardAnimation(isVisible.value){
            CreateDetailedCard()
        }

    }

}



@Preview(showBackground = true)
@Composable
fun PreviewCard(){
    val image = Image(height = 640, url="https://i.scdn.co/image/d3acaeb069f37d8e257221f7224c813c5fa6024e", width = 640)
    val images = listOf (image)
    val album = Album(
        images = images,
        album_type = null,
        artists = null,
        available_markets = null,
        external_urls = null,
        href = null,
        id = null,
        name = null,
        release_date = null,
        release_date_precision =null,
        total_tracks = null,
        type = null,
        uri = null
    )
    val spoty = Spotify(
        external_urls = ExternalUrlsXXX(spotify = "https://open.spotify.com/track/1lgN0A2Vki2FTON5PYq42m"),
        album = album,
        artists = null,
        available_markets = null,
        disc_number = null,
        duration_ms = null,
        explicit = null,
        external_ids = null,
        href = null,
        id = null,
        is_local = null,
        name = null,
        popularity = null,
        track_number = null,
        type = null,
        uri = null
    )
    val song = SongResult(
        title = "Warriors",
        artist = "Imagine Dragons",
        album = "Warriors",
        release_date = "2-2-2024",
        apple_music = null,
        label = null,
        song_link = null,
        spotify = spoty,
        timecode = null
    )
    val isVisible = remember { mutableStateOf(true) }
    val card =  DetailedCardSong()
    card.showDetailCard(song,isVisible)

}

