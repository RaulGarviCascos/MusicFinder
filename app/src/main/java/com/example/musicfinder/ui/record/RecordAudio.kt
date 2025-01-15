package com.example.musicfinder.ui.record
import android.media.MediaPlayer
import android.media.MediaRecorder
import java.io.IOException
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import android.Manifest
import java.io.File

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

    private fun startRecording(fileName:String) {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed: ${e.message}")
            }
            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

     fun onRecord(start: Boolean,fileName:String) = if (start) {
        startRecording(fileName = fileName)
    } else {
        stopRecording()
     }




}