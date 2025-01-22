package com.example.musicfinder.ui.record
import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import java.io.IOException
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.musicfinder.data.model.AudDResponseModels.SongResult
import com.example.musicfinder.data.repository.RecognizeAudio
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun RecordAudioWrapper(permissions:Array<String>, onPermissionGranted: () -> Unit) {
    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            onPermissionGranted()
        } else {
            Toast.makeText(context, "Permiso de grabación denegado", Toast.LENGTH_SHORT).show()
        }
    }
    for(permission in permissions){
        LaunchedEffect(Unit) {
            permissionLauncher.launch(permission)
        }
    }
}

object RecordAudio  {

    private var player: MediaPlayer? = null
    private var recorder: MediaRecorder? = null
    private val LOG_TAG = "AudioRecordTest"
    private var maxRetries = 3
    private var retryCount = 0

    fun startPlaying(fileName:String, onCompletion: () -> Unit) {

        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
                // Callback cuando el audio termina
                setOnCompletionListener {
                    onCompletion()
                    stopPlaying()
                }
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    fun stopPlaying() {
        player?.release()
        player = null
    }

    private fun startRecording(fileName:String,context: Context,songResult: MutableState<SongResult?>,listening: MutableState<Boolean>) {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setAudioEncodingBitRate(128000)
            setAudioSamplingRate(44100) 

            try {
                prepare()
                CoroutineScope(Dispatchers.Main).launch {
                    delay(3500) // 3.5 segundos
                    stopRecording(fileName, context,songResult,listening)
                }
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed: ${e.message}")
            }

        }
    }


    private fun stopRecording(fileName: String, context: Context,songResult: MutableState<SongResult?>,listening: MutableState<Boolean>) {
        recorder?.apply {
            stop()
            release()
            CoroutineScope(Dispatchers.Main).launch{
                val result = RecognizeAudio.run(fileName,context)
                if(result != null){
                    songResult.value = result
                    listening.value=false
                }else if(retryCount<maxRetries){
                    retryCount++
                    startRecording(fileName,context, songResult,listening)
                }else{
                    println("Max retries reached. Stopping attempts.")
                    listening.value=false
                }

            }
        }
        recorder = null
    }



     fun onRecord(listening: MutableState<Boolean>,fileName:String,context: Context,songResult: MutableState<SongResult?>) = if (listening.value) {
             startRecording(fileName = fileName,context,songResult,listening)
         } else {
             stopRecording(fileName,context,songResult,listening)
         }

}