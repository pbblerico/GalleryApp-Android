package com.example.galleryapp.data.di.repository

import com.example.galleryapp.PicturePagingSource
import com.example.galleryapp.data.network.api.PicturesApi
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import com.example.galleryapp.data.repositories.pictures.PicturesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object PictureRepositoryModule {

    @Provides
    @Singleton
    fun providePicturePaging(api: PicturesApi) = PicturePagingSource(api)

    @Singleton
    @Provides
    fun providePictureRepository(api: PicturesApi, pagingSource: PicturePagingSource): PicturesRepository = PicturesRepositoryImpl(api, pagingSource)
}