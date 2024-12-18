package com.example.musicfinder.ui.historical
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.musicfinder.data.model.Song
import coil.compose.AsyncImage
import com.example.musicfinder.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardSong(song:Song,placeholderResId: Int? = null) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 80.dp)

    ) {
        Row() {
            if (placeholderResId != null) {
                Image(
                    painter = painterResource(id = placeholderResId),
                    contentDescription = "Imagen desde recurso local",
                    modifier = Modifier.size(80.dp) // Tamaño de la imagen
                )
            } else {
                AsyncImage(
                    model = song.url_image,
                    contentDescription = "Imagen desde URL",
                    modifier = Modifier.size(80.dp) // Tamaño de la imagen
                )
            }
            Column(

            ){
                Text(
                    text = song.title,
                    modifier = Modifier
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp
                )
                Text(
                    text = song.artist,
                    modifier = Modifier
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp
                )
            }

        }

    }
}


@Composable
fun ImageFromUrl(url: String) {
    AsyncImage(
        model = url,
        contentDescription = "Imagen desde URL"
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewTab(){
    var song = Song( title = "Warriors",artist="Imagine Dragons",album="Warriors",url_image="https://i.scdn.co/image/b039549954758689330893bd4a92585092a81cf5",url_spotify="https://open.spotify.com/artist/53XhwfbYqKCa1cC15pYq2q")
    CardSong(
        song.copy(
            url_image = "" // Deja vacío para usar el marcador de posición en el Preview
        ),R.drawable.ic_placeholder_image
    )
}

