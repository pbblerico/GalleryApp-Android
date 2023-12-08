package com.example.galleryapp.data.di.repository

import com.example.galleryapp.data.network.api.PicturesApi
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserGalleryRepository
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserGalleryRepositoryImpl
import com.example.galleryapp.data.repositories.pictures.PicturePagingSource
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import com.example.galleryapp.data.repositories.pictures.PicturesRepositoryImpl
import com.google.firebase.database.DatabaseReference
import com.google.firebase.storage.StorageReference
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
    fun providePictureRepository(api: PicturesApi, pagingSource: PicturePagingSource, storage: StorageReference): PicturesRepository = PicturesRepositoryImpl(api, pagingSource, storage)


    @Singleton
    @Provides
    fun provideFRDBUserGalleryRepository(databaseReference: DatabaseReference): FRDBUserGalleryRepository = FRDBUserGalleryRepositoryImpl(databaseReference)
}