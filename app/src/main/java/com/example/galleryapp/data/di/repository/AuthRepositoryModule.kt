package com.example.galleryapp.data.di.repository

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.example.galleryapp.data.repositories.authorization.AuthRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
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
    fun provideAuthRepository(firebaseAuth: FirebaseAuth, firebaseDatabase: FirebaseDatabase): AuthRepository = AuthRepositoryImpl(firebaseAuth, firebaseDatabase)
}