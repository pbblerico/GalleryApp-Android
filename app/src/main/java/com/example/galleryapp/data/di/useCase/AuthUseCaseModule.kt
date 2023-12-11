package com.example.galleryapp.data.di.useCase

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserRepository
import com.example.galleryapp.domain.useCases.authorization.CurrentUserInteraction
import com.example.galleryapp.domain.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.domain.useCases.authorization.LogOutInteraction
import com.example.galleryapp.domain.useCases.authorization.LogOutUseCase
import com.example.galleryapp.domain.useCases.authorization.LoginInteraction
import com.example.galleryapp.domain.useCases.authorization.LoginUseCase
import com.example.galleryapp.domain.useCases.authorization.SignUpInteraction
import com.example.galleryapp.domain.useCases.authorization.SignUpUseCase
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
    fun provideSignUpUseCase(authRepo: AuthRepository, userRepo: FRDBUserRepository): SignUpUseCase = SignUpInteraction(authRepo, userRepo)

    @Provides
    fun provideCurrentUserUseCase(repo: AuthRepository): CurrentUserUseCase = CurrentUserInteraction(repo)


    @Provides
    fun logOutUseCase(repo: AuthRepository): LogOutUseCase = LogOutInteraction(repo)
}