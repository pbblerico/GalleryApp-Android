package com.example.galleryapp.data.useCases.pictures

import com.example.galleryapp.data.models.Image
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserGalleryRepository
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import com.example.galleryapp.utils.Resource
import javax.inject.Inject

interface GetGalleryUseCase {
    suspend fun getStoragePics(uid: String, onResult: (Resource<List<Image>>) -> Unit)

    suspend fun getFRDBPics(uid: String, onResult: (Resource<List<Int>>) -> Unit)
}

class GetGalleryInteraction @Inject constructor(
    private val picRepo: PicturesRepository,
    private val savedRepo: FRDBUserGalleryRepository
): GetGalleryUseCase {

    override suspend fun getStoragePics(uid: String, onResult: (Resource<List<Image>>) -> Unit) {
        picRepo.getPublicImages(uid, onResult)
    }

    override suspend fun getFRDBPics(uid: String, onResult: (Resource<List<Int>>) -> Unit) {
        savedRepo.getSaved(uid, onResult)
    }
}