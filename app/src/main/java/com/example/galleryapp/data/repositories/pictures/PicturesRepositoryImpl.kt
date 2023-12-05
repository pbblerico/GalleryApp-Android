package com.example.galleryapp.data.repositories.pictures

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.galleryapp.PicturePagingSource
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.models.PictureResponse
import com.example.galleryapp.data.network.api.PicturesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PicturesRepositoryImpl @Inject constructor(
    private val api: PicturesApi, private val pagingSource: PicturePagingSource
) : PicturesRepository {
    override suspend fun getCuratedPictures(page: Int, per_page: Int): PictureResponse? {
        val result = api.getCuratedPhotos(page)
        return if (result.isSuccessful) {
            result.body()
        } else throw Exception(result.errorBody().toString())
    }

    override var pictureFlow: Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false, initialLoadSize = 1),
        pagingSourceFactory = { pagingSource }
    ).flow

}