package com.example.musicfinder.ui.record
import android.media.MediaPlayer
import android.media.MediaRecorder
import java.io.IOException
import android.util.Log

private const val LOG_TAG = "AudioRecordTest"
private const val REQUEST_RECORD_AUDIO_PERMISSION = 200

class RecordAudio  {

    private var player: MediaPlayer? = null
    private var recorder: MediaRecorder? = null


    private fun startPlaying(fileName:String) {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Log.e(LOG_TAG, "prepare() failed")
            }
        }
    }

    private fun stopPlaying() {
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
                Log.e(LOG_TAG, "prepare() failed")
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

    fun onPlay(start: Boolean,fileName:String) = if (start) {
        startPlaying(fileName = fileName)
    } else {
        stopPlaying()
    }


}