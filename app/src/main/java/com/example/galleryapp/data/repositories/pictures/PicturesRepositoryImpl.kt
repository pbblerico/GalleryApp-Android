package com.example.galleryapp.data.repositories.pictures

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.galleryapp.PicturePagingSource
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.models.PictureResponse
import com.example.galleryapp.data.network.api.PicturesApi
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PicturesRepositoryImpl @Inject constructor(
    private val api: PicturesApi, private val pagingSource: PicturePagingSource,
    private val storage: StorageReference
) : PicturesRepository {
    override suspend fun getCuratedPictures(page: Int, perPage: Int): PictureResponse? {
        val result = api.getCuratedPhotos(page)
        return if (result.isSuccessful) {
            result.body()
        } else throw Exception(result.errorBody().toString())
    }

    override var pictureFlow: Flow<PagingData<Photo>> = Pager(
        config = PagingConfig(20, enablePlaceholders = false, initialLoadSize = 1),
        pagingSourceFactory = { pagingSource }
    ).flow

    override suspend fun getImages(path: String, onSuccess: (List<String>) -> Unit) {

        val imageUrls = mutableListOf<String>()
        val directory = storage.child(path)
        directory.listAll().
                addOnSuccessListener {listResult ->
                    var downloadedCount = 0
                    for (item in listResult.items) {
                        item.downloadUrl.addOnSuccessListener { uri ->
                            val imageUrl = uri.toString()
                            imageUrls.add(imageUrl)
                            downloadedCount++
                            if (downloadedCount == listResult.items.size) {
                                onSuccess.invoke(imageUrls)
                            }
                        }.addOnFailureListener { exception ->

                        }
                    }

                }
    }

}