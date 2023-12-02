package com.example.galleryapp

import com.example.galleryapp.data.models.PictureResponse
import com.example.galleryapp.data.network.api.PicturesApi
import javax.inject.Inject

class PicturesRepositoryImpl @Inject constructor(
    private val api: PicturesApi
): PicturesRepository {
    override suspend fun getCuratedPictures(): PictureResponse? {
        val response = api.getCuratedPhotos()
        if (response.isSuccessful) return response.body()
        else throw Exception(response.errorBody().toString())
    }
}