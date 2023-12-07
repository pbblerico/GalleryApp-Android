package com.example.galleryapp.data.repositories.pictures

import androidx.paging.PagingData
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.models.PictureResponse
import com.example.galleryapp.utils.Resource
import kotlinx.coroutines.flow.Flow

interface PicturesRepository {

    suspend fun getCuratedPictures(page: Int = 1, perPage: Int = 20): PictureResponse?
    var pictureFlow: Flow<PagingData<Photo>>

    suspend fun getPictureById(id: Int): Photo?


    suspend fun getPrivateImages(authorUID: String, onResult: (Resource<List<Image>>) -> Unit)

    suspend fun getPublicImages(authorUID: String, onResult: (Resource<List<Image>>) -> Unit)
}