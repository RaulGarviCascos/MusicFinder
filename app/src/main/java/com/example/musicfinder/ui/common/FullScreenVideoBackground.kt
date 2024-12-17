package com.example.musicfinder.ui.common


import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.material3.*
import com.example.musicfinder.R

@Composable
fun FullScreenVideoBackground() {
    val context = LocalContext.current

    Box() {
        // VideoView como fondo
        AndroidView(
            factory = { ctx ->
                VideoView(ctx).apply {
                    setVideoURI(Uri.parse("android.resource://${ctx.packageName}/${R.raw.wallpaper_musicfinder_video}"))
                    setOnPreparedListener {
                        it.isLooping = true
                        it.start()
                    }
                }
            },
            modifier = Modifier
                .fillMaxHeight()

        )



    }
}
