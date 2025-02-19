package com.example.musicfinder.ui.historical
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import com.example.musicfinder.R
import com.example.musicfinder.data.model.AudDResponseModels.SongResult


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListCardSong(song:SongResult,placeholderResId: Int? = null) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier.fillMaxWidth().height(88.dp).padding(5.dp)
    ) {
        Row(
        ) {
            ImageFromUrlSafe(song.spotify?.album?.images?.get(1)?.url,80)
            Column(
            ){
                song.title?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 23.sp
                    )
                }
                song.artist?.let {
                    Text(
                        text = it,
                        modifier = Modifier
                            .padding(5.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 18.sp
                    )
                }
            }

        }

    }
}


@Composable
fun ImageFromUrlSafe(url: String?,size: Int) {
    val painter = rememberAsyncImagePainter(model = url)
    val state = painter.state.collectAsState().value

    when (state) {
        is AsyncImagePainter.State.Loading -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder_image),
                    contentDescription = "Cargando",
                    modifier = Modifier.size(size.dp).padding(5.dp).clip(RoundedCornerShape(10.dp)),
                    alignment =  Alignment.Center
                )
        }
        is AsyncImagePainter.State.Error -> {
                Image(
                    painter = painterResource(id = R.drawable.ic_placeholder_image),
                    contentDescription = "Error",
                    modifier = Modifier.size(size.dp).padding(5.dp).clip(RoundedCornerShape(10.dp)),
                    alignment =  Alignment.Center
                )
        }
        else -> {
                Image(
                    painter = painter,
                    contentDescription = "Imagen desde URL",
                    modifier = Modifier.size(size.dp).padding(5.dp).clip(RoundedCornerShape(10.dp)),
                    alignment =  Alignment.Center
                )
        }
    }
}

