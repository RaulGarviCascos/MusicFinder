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
import kotlinx.coroutines.withContext

object RecognizeAudio {

    suspend fun run(filePath: String,context: Context): SongResult? {


            val securePrefs = getSecurePreferences(context)
            val apiToken = securePrefs.getString("api_token", null)
            val tokenBody =  apiToken?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }
            val file = File(filePath)
            val requestBody = RequestBody.create("audio/mp4".toMediaTypeOrNull(), file)
            val filePart = MultipartBody.Part.createFormData("file", file.name, requestBody)
            val returnParams = RequestBody.create("text/plain".toMediaTypeOrNull(), "spotify")
            try {
                val response = tokenBody?.let { ApiClient.apiService.recognizeSong(it, filePart, returnParams) }
                    if (response != null && response.isSuccessful && response.body()?.result != null) {
                        val result = response.body()
                        //add song
                        val repository = SongRepository(context)
                        var songs: List<SongResult?> = repository.getSongResults("Songs")
                        repository.saveSongResult("Songs", songs + result!!.result)
                        return result.result
                    } else {
                        println("Recognition Failed: ${response?.errorBody()?.string()}")

                    }
            } catch (e: Exception) {
                println("Error: ${e.message}")

            }
        return null
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

