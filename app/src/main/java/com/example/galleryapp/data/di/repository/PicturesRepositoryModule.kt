package com.example.galleryapp.data.di.repository

import android.util.Log
import com.example.galleryapp.PicturesRepository
import com.example.galleryapp.PicturesRepositoryImpl
import com.example.galleryapp.data.network.api.PicturesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object PicturesRepositoryModule {

    @Provides
    @Singleton
    fun getPicturesRepository(api: PicturesApi): PicturesRepository {
        Log.d("module", "pictures module")
        return PicturesRepositoryImpl(api)
    }
}