package com.example.musicfinder.data.model.AudDResponseModels

data class AppleMusic(
    val albumName: String,
    val artistName: String,
    val artwork: Artwork,
    val composerName: String,
    val discNumber: Int,
    val durationInMillis: Int,
    val genreNames: List<String>,
    val isrc: String,
    val name: String,
    val playParams: PlayParams,
    val previews: List<Preview>,
    val releaseDate: String,
    val trackNumber: Int,
    val url: String
)