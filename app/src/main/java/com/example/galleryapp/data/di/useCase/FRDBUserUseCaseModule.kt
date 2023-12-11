package com.example.galleryapp.data.di.useCase

import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserRepository
import com.example.galleryapp.domain.useCases.user.GetUserInfoInteraction
import com.example.galleryapp.domain.useCases.user.UserInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object FRDBUserUseCaseModule
{

    @Provides
    fun provideUserInfoUseCase(userRepo: FRDBUserRepository): UserInfoUseCase = GetUserInfoInteraction(userRepo)
}