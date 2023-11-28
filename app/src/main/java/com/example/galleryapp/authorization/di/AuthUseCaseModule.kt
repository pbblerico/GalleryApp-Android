package com.example.galleryapp.authorization.di

import com.example.galleryapp.authorization.domain.repositories.AuthRepository
import com.example.galleryapp.authorization.domain.useCases.LoginInteraction
import com.example.galleryapp.authorization.domain.useCases.LoginUseCase
import com.example.galleryapp.authorization.domain.useCases.SignUpInteraction
import com.example.galleryapp.authorization.domain.useCases.SignUpUseCase
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