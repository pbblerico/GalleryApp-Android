package com.example.galleryapp.data.network.api

import com.example.galleryapp.data.models.PictureResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface PicturesApi {
    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("per_page") perPage: Int = 20,
        @Header("Authorization") apiKey: String = "idFDko4NQn427AkjVbkDnMgakwRHEXYp57Puweujd24sXb3efHsvrXm6"
    ): Response<PictureResponse>
}