package com.example.galleryapp.domain.useCases.authorization

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import javax.inject.Inject

interface LogOutUseCase {
    suspend fun execute()

}

class LogOutInteraction @Inject constructor(private val repo: AuthRepository): LogOutUseCase {
    override suspend fun execute() {
        return repo.logout()
    }

}