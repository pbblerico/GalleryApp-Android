package com.example.galleryapp.data.di.useCase

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.example.galleryapp.data.useCases.authorization.LoginInteraction
import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import com.example.galleryapp.data.useCases.authorization.SignUpInteraction
import com.example.galleryapp.data.useCases.authorization.SignUpUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
object AuthUseCaseModule {

    @Provides
    fun provideLoginUseCase(repo: AuthRepository): LoginUseCase = LoginInteraction(repo)

    @Provides
    fun provideSignUpUseCase(repo: AuthRepository): SignUpUseCase = SignUpInteraction(repo)
}