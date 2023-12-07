package com.example.galleryapp.data.di.useCase

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.example.galleryapp.data.useCases.authorization.CurrentUserInteraction
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.data.useCases.authorization.LogOutInteraction
import com.example.galleryapp.data.useCases.authorization.LogOutUseCase
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

    @Provides
    fun provideCurrentUserUseCase(repo: AuthRepository): CurrentUserUseCase = CurrentUserInteraction(repo)


    @Provides
    fun logOutUseCase(repo: AuthRepository): LogOutUseCase = LogOutInteraction(repo)
}