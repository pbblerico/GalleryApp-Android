package com.example.galleryapp.data.useCases.authorization

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import javax.inject.Inject


//todo (change use case)
interface LoginUseCase {
    suspend fun execute(email: String, password: String)
}

class LoginInteraction @Inject constructor(private val repo: AuthRepository): LoginUseCase {
    override suspend fun execute(email: String, password: String) {
        repo.login(email, password)
    }

}