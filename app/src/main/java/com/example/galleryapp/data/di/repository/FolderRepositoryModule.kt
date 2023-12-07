package com.example.galleryapp.data.di.repository

import com.example.galleryapp.data.repositories.folder.FoldersRepository
import com.example.galleryapp.data.repositories.folder.FoldersRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object FolderRepositoryModule {

    @Provides
    @Singleton
    fun provideFolderRepository(/*storageReference: StorageReference*/): FoldersRepository = FoldersRepositoryImpl(/*storageReference*/)
}