package com.example.galleryapp.domain.useCases.authorization

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.google.firebase.auth.AuthResult
import javax.inject.Inject


interface LoginUseCase {
    suspend fun execute(email: String, password: String): AuthResult?
}

class LoginInteraction @Inject constructor(private val repo: AuthRepository): LoginUseCase {
    override suspend fun execute(email: String, password: String): AuthResult? {
        return repo.login(email, password)
    }

}