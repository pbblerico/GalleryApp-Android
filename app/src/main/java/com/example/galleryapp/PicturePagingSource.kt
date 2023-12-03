package com.example.galleryapp

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.network.api.PicturesApi

class PicturePagingSource(private val api: PicturesApi): PagingSource<Int, Photo>() {
    override fun getRefreshKey(state: PagingState<Int, Photo>): Int?{
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val response = api.getCuratedPhotos().body()

            val pageNumber = params.key ?: 1

//            val prevKey = if ((response?.page ?: 1) > 1) ((response?.page ?: 1) - 1) else null
//            var nextKey = if ((response?.page ?: 1) < (response?.totalPages ?: 1)) (response?.page ?: 1) + 1 else null

            val prevKey = if (pageNumber > 1) pageNumber - 1 else null
            var nextKey = if (pageNumber < (response?.totalPages ?: 1)) pageNumber + 1 else null

            LoadResult.Page(
                data = response?.photos ?: listOf(),
                prevKey = null,
                nextKey = nextKey
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}