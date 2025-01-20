package com.example.musicfinder.ui.historical
import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicfinder.data.model.Song
import com.example.musicfinder.R
import android.net.Uri
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import com.example.musicfinder.data.model.AudDResponseModels.SongResult

@Composable
fun DetailedCard(song:SongResult) {
    val context = LocalContext.current
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
        modifier = Modifier
            .width(300.dp)
            .height(400.dp)
            .padding(5.dp)
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
                        text = "Title: "+song.title,
                        modifier = Modifier
                            .padding(start = 60.dp,5.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 23.sp
                    )
                    Text(
                        text ="Artist: "+ song.artist,
                        modifier = Modifier
                            .padding(start = 60.dp,5.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp
                    )
                    Text(
                        text ="Album: "+ song.album,
                        modifier = Modifier
                            .padding(start = 60.dp,5.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp
                    )
                    Text(
                        text ="Release date: "+ song.release_date,
                        modifier = Modifier
                            .padding(start = 60.dp,5.dp),
                        textAlign = TextAlign.Left,
                        fontSize = 18.sp
                    )
                    Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center){
                        Button(onClick = {
                            val uri = Uri.parse(song.spotify?.album?.artists?.get(0)?.external_urls?.spotify)
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



//@Preview(showBackground = true)
//@Composable
//fun PreviewCard(){
//    var song = Song( title = "Warriors",artist="Imagine Dragons",album="Warriors",url_image="https://i.scdn.co/image/b039549954758689330893bd4a92585092a81cf5",url_spotify="https://open.spotify.com/artist/53XhwfbYqKCa1cC15pYq2q", date_finded = "2-2-2024")
//    DetailedCard(song.copy(url_image = ""))
//}

