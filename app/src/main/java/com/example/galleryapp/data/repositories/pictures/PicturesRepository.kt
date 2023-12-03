package com.example.galleryapp.data.repositories.pictures

import androidx.paging.PagingData
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.models.PictureResponse
import kotlinx.coroutines.flow.Flow

interface PicturesRepository {

    suspend fun getCuratedPictures(): PictureResponse?
    var pictureFlow: Flow<PagingData<Photo>>
}