package com.example.musicfinder.data.repository
import com.google.gson.Gson
import android.content.Context
import com.example.musicfinder.data.model.Song
import com.google.gson.reflect.TypeToken

class SongRepository (context: Context){
    private val sharedPreferences = context.getSharedPreferences("Songs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun saveSongs(key: String, songs: List<Song>) {
        val json = gson.toJson(songs)
        sharedPreferences.edit().putString(key, json).apply()
    }

    fun getSongs(key: String): List<Song> {
        val json = sharedPreferences.getString(key, null) ?: return emptyList()
        val type = object : TypeToken<List<Song>>() {}.type
        return gson.fromJson(json, type)
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}