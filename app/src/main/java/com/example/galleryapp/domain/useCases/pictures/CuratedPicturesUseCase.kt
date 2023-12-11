package com.example.galleryapp.domain.useCases.pictures

import com.example.galleryapp.data.models.PictureResponse
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import javax.inject.Inject

interface CuratedPicturesUseCase {
    suspend fun execute(page: Int, perPage: Int): PictureResponse?
}

class GetCuratedPictures @Inject constructor(val repo: PicturesRepository): CuratedPicturesUseCase {
    override suspend fun execute(page: Int, perPage: Int): PictureResponse? {
        return repo.getCuratedPictures(page, perPage)
    }


}