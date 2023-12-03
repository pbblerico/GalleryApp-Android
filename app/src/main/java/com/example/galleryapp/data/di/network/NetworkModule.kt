package com.example.galleryapp.data.di.network

import com.example.galleryapp.data.network.api.PicturesApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    @PictureToken
    @Provides
    fun pictureToken() = "idFDko4NQn427AkjVbkDnMgakwRHEXYp57Puweujd24sXb3efHsvrXm6"

    @LoggingInterceptor
    @Provides
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)

    @Provides
    fun provideHeaderInterceptor(@PictureToken token: String) = Interceptor { chain ->
        val request = chain.request().newBuilder().header("Authorization", token).build()
        return@Interceptor chain.proceed(request)
    }

    @Provides
    fun provideClient(
        @LoggingInterceptor loggingInterceptor: Interceptor, headerInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor).build()
    }

    @PictureUrl
    @Provides
    @Singleton
    fun providePicturesRetrofit(@PictureUrl url: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(url).client(client)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun providePicturesApi(@PictureUrl retrofit: Retrofit): PicturesApi {
        return retrofit.create(PicturesApi::class.java)
    }


}

@Qualifier
annotation class PictureUrl

@Qualifier
annotation class PictureToken

@Qualifier
annotation class LoggingInterceptor