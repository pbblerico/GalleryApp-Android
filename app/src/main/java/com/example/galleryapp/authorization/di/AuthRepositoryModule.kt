package com.example.galleryapp.authorization.di

import com.example.galleryapp.authorization.domain.repositories.AuthRepository
import com.example.galleryapp.authorization.domain.repositories.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AuthRepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository = AuthRepositoryImpl(firebaseAuth)
}