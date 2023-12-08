package com.example.galleryapp.data.di.useCase

import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserGalleryRepository
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import com.example.galleryapp.data.useCases.pictures.CuratedPicturesUseCase
import com.example.galleryapp.data.useCases.pictures.GetCuratedPictures
import com.example.galleryapp.data.useCases.pictures.GetGalleryInteraction
import com.example.galleryapp.data.useCases.pictures.GetGalleryUseCase
import com.example.galleryapp.data.useCases.pictures.GetPictureById
import com.example.galleryapp.data.useCases.pictures.GetPictureByIdUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object PictureUseCaseModule
{
    @Provides
    fun provideCuratedPicturesUseCase(repo: PicturesRepository): CuratedPicturesUseCase = GetCuratedPictures(repo)


    @Provides
    fun providePictureByIdUseCase(repo: PicturesRepository): GetPictureByIdUseCase = GetPictureById(repo)


    @Provides
    fun provideGalleryUseCase(userRepo: FRDBUserGalleryRepository, picsRepo: PicturesRepository): GetGalleryUseCase = GetGalleryInteraction(picsRepo, userRepo)
}