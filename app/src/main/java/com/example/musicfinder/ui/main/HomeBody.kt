package com.example.musicfinder.ui.main

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.musicfinder.R
import com.example.musicfinder.data.model.AudDResponseModels.SongResult
import com.example.musicfinder.ui.animations.ListeningAnimation
import com.example.musicfinder.ui.historical.DetailedCardSong
import com.example.musicfinder.ui.record.RecordAudio
import com.example.musicfinder.ui.record.RecordAudioWrapper
import com.example.musicfinder.ui.record.RecordButton
import kotlinx.coroutines.CoroutineScope


@Composable
fun HomeBody(
    topPadding: PaddingValues,
    snackbarHostState: SnackbarHostState,
    coroutineScope: CoroutineScope
) {
    val listening = remember { mutableStateOf(false) }
    val permissionGranted = remember { mutableStateOf(false) }
    val context = LocalContext.current
    val fileName = "${context.externalCacheDir?.absolutePath}/audiorecordtest.mp4"
    val permissions = arrayOf(
        Manifest.permission.RECORD_AUDIO,
        Manifest.permission.READ_MEDIA_AUDIO
    )
    val justPressed = remember { mutableStateOf(false) }
    val song = remember { mutableStateOf<SongResult?>(null) }
    val songIsShowing = remember { mutableStateOf(true) }

    // Solicitar permisos antes de interactuar
    RecordAudioWrapper(permissions) {
        permissionGranted.value = true
    }
    var listenText = if (listening.value){
        stringResource(id = R.string.listening)
    }else{
        stringResource(id = R.string.press_listen)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(topPadding),
    ) {
        if (song.value!=null && songIsShowing.value){
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
            val detailedCard = DetailedCardSong()
            detailedCard.showDetailCard(song= song.value?:emptySong,songIsShowing)
        }else{
            ListeningAnimation(listening.value)
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            )
            {
                RecordButton(onClick = {
                    listening.value = !listening.value
                    justPressed.value = true
                })
                if (justPressed.value) {
                    RecordAudio.OnRecord(listening, fileName = fileName, context, song,snackbarHostState,coroutineScope)
                    justPressed.value = false
                }
                Text(
                    text = listenText,
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Cyan,
                        fontWeight = FontWeight.Bold,
                        shadow = Shadow(
                            color = Color.Black,
                            offset = Offset(-4f, 4f),
                            blurRadius = 2f
                        )
                    )
                )

            }
        }

    }

}
