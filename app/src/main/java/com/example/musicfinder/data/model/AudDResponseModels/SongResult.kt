package com.example.musicfinder.data.model.AudDResponseModels

data class  SongResult(
    val album: String?,
    val apple_music: AppleMusic?,
    val artist: String?,
    val label: String?,
    val release_date: String?,
    val song_link: String?,
    val spotify: Spotify?,
    val timecode: String?,
    val title: String?
)