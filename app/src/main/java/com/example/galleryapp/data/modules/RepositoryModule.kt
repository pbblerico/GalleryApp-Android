package com.example.galleryapp.data.modules

import com.example.galleryapp.authorization.domain.repositories.AuthRepositoryImpl
import com.example.galleryapp.authorization.domain.repositories.AuthRepostitory
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepostitory = AuthRepositoryImpl(firebaseAuth)
}