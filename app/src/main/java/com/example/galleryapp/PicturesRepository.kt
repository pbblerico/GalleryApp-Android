package com.example.galleryapp

import com.example.galleryapp.data.models.PictureResponse

interface PicturesRepository {
    suspend fun getCuratedPictures(): PictureResponse?
}