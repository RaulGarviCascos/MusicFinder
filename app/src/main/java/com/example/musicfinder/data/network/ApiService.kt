package com.example.musicfinder.data.network

import com.example.musicfinder.data.model.AudDResponseModels.AudDResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import kotlin.contracts.Returns

val spotify = "spotify"

interface ApiService{
    @Multipart
    @POST("/recognize")
    suspend fun recognizeSong(
        @Part("api_token") apiToken: RequestBody,
        @Part file: MultipartBody.Part,
        @Part("return") returnParam:RequestBody
    ): Response<AudDResponse>
}

