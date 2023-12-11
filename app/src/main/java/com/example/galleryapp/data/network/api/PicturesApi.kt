package com.example.galleryapp.data.network.api

import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.models.PictureResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PicturesApi {
    @GET("curated")
    suspend fun getCuratedPhotos(
        @Query("per_page") perPage: Int,
        @Query("page") page: Int
    ): Response<PictureResponse>

    @GET("photos/{id}")
    suspend fun getPhotoById(
        @Path("id") id: Int
    ): Response<Photo>

    @GET("search")
    suspend fun searchForPhotos(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<PictureResponse>
}