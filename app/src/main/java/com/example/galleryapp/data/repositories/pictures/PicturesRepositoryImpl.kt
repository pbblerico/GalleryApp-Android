package com.example.galleryapp.data.repositories.pictures

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.galleryapp.PicturePagingSource
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.models.PictureResponse
import com.example.galleryapp.data.network.api.PicturesApi
import com.example.galleryapp.utils.Resource
import com.google.firebase.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
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

    override suspend fun getPictureById(id: Int): Photo? {
        val result = api.getPhotoById(id)
        return if (result.isSuccessful) {
            result.body()
        } else throw Exception(result.errorBody().toString())
    }

    override suspend fun getPrivateImages(
        authorUID: String,
        onResult: (Resource<List<Image>>) -> Unit
    ) {
        getImages("users/${authorUID}/images/private", onResult)
    }

    override suspend fun getPublicImages(
        authorUID: String,
        onResult: (Resource<List<Image>>) -> Unit
    ) {
        getImages("users/${authorUID}/images/public", onResult)
    }

    private fun getImages(path: String, onResult: (Resource<List<Image>>) -> Unit) {

        val imageUrls = mutableListOf<Image>()
        val directory = Firebase.storage.reference.child(path)
        directory.listAll().addOnSuccessListener { listResult ->
            var downloadedCount = 0
            for (item in listResult.items) {
                item.downloadUrl.addOnSuccessListener { uri ->
                    val imageUrl = uri.toString()
                    imageUrls.add(Image(imageUrl))
                    downloadedCount++
                    if (downloadedCount == listResult.items.size) {
                        onResult.invoke(
                            Resource.Success(imageUrls)
                        )
                    }
                }.addOnFailureListener { exception ->
                    onResult.invoke(
                        Resource.Failure(exception.message.orEmpty())
                    )
                }
            }

        }
    }

}