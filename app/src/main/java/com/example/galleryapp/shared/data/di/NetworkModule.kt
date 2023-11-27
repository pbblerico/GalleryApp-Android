package com.example.galleryapp.shared.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @PictureUrl
    @Provides
    fun pictureUrl() = "https://api.pexels.com/v1/"

    @Provides
    @Singleton
    fun getPicturesRetrofit(@PictureUrl url: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Qualifier
    annotation class PictureUrl

}