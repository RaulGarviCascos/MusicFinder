package com.example.musicfinder.ui.main
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.example.musicfinder.data.model.AudDResponseModels.SongResult
import com.example.musicfinder.data.repository.SongRepository
import com.example.musicfinder.ui.historical.DetailedCardSong
import com.example.musicfinder.ui.historical.ListCardSong


@Composable
fun HistoricalBody(padding : PaddingValues) {

    Box(
        modifier = Modifier.fillMaxSize().padding(padding)
    ) {
        val context = LocalContext.current
        val repository = SongRepository(context)

        val savedSongs = repository.getSongResults(key="Songs")

        val emptySong = SongResult(
            album = null,
            apple_music = null,
            artist = null,
            label = null,
            release_date = null,
            song_link = null,
            spotify = null,
            timecode = null,
            title = null
        )
        var selectedSong = remember { mutableStateOf<SongResult?>(null) } // Estado para la canción seleccionada
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
        val detailedCard = DetailedCardSong()
        detailedCard.showDetailCard(song= selectedSong.value?:emptySong,isVisible)

    }

}
