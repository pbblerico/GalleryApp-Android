package com.example.galleryapp.data.useCases.pictures

import com.example.galleryapp.data.models.Photo
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import javax.inject.Inject

interface GetPictureByIdUseCase {
    suspend fun execute(id: Int): Photo?
}

class GetPictureById @Inject constructor(val repo: PicturesRepository): GetPictureByIdUseCase {
    override suspend fun execute(id: Int): Photo? {
        return repo.getPictureById(id)
    }


}