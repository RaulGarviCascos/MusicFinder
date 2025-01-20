package com.example.musicfinder.data.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.musicfinder.data.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.musicfinder.data.model.AudDResponseModels.SongResult

object RecognizeAudio {

    fun run(filePath: String,context: Context) {

        val securePrefs = getSecurePreferences(context)
        val apiToken = securePrefs.getString("api_token", null)

        val tokenBody = apiToken?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }

        val file = File(filePath)

        val requestBody = RequestBody.create("audio/mp4".toMediaTypeOrNull(), file)
        val filePart = MultipartBody.Part.createFormData("file", file.name, requestBody)
        val returnParams = RequestBody.create("text/plain".toMediaTypeOrNull(),"spotify")
        CoroutineScope(Dispatchers.IO).launch {
            try {

                val response = tokenBody?.let { ApiClient.apiService.recognizeSong(it, filePart,returnParams) }
                if (response!=null){
                    if (response.isSuccessful && response.body()?.result != null) {
                        val result = response.body()
                        println("Recognition Success: $result")
                        val repository = SongRepository(context)
                        var songs : List<SongResult?> = repository.getSongResults("Songs")
                        repository.saveSongResult("Songs", songs+result!!.result)
                    } else {
                        println("Recognition Failed: ${response.errorBody()?.string()}")
                    }
                }

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

   fun getSecurePreferences(context: Context): SharedPreferences {
        val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

        return EncryptedSharedPreferences.create(
            "secure_prefs",
            masterKeyAlias,
            context,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )
    }
}

