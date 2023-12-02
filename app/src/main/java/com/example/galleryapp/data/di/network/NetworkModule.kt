package com.example.galleryapp.data.di.network

import android.util.Log
import com.example.galleryapp.data.network.api.PicturesApi
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


    @PictureUrl
    @Provides
    @Singleton
    fun getPicturesRetrofit(@PictureUrl url: String): Retrofit {
        Log.d("network", "retrofit")

        return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun getPicturesApi(@PictureUrl retrofit: Retrofit): PicturesApi {
        Log.d("network", "api")

        return retrofit.create(PicturesApi::class.java)
    }
}

@Qualifier
annotation class PictureUrl
