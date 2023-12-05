package com.example.galleryapp.data.repositories.pictures

import androidx.paging.PagingData
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.models.PictureResponse
import kotlinx.coroutines.flow.Flow

interface PicturesRepository {

    suspend fun getCuratedPictures(page: Int = 1, per_page: Int = 20): PictureResponse?
    var pictureFlow: Flow<PagingData<Photo>>
}