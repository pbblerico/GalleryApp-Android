package com.example.galleryapp.data.repositories.pictures

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.network.api.PicturesApi

class PicturePagingSource(private val api: PicturesApi): PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? = 0
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val pageNumber = params.key ?: 1

            val response = api.getCuratedPhotos(page = pageNumber, perPage = 20).body()

            val nextPage = if (pageNumber < (response?.totalPages ?: 1)) pageNumber + 1 else null
            LoadResult.Page(
                data = response?.photos ?: listOf(),
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}