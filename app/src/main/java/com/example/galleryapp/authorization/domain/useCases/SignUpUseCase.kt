package com.example.galleryapp.authorization.domain.useCases

import com.example.galleryapp.authorization.domain.repositories.AuthRepository
import javax.inject.Inject

interface SignUpUseCase {
    suspend fun execute(nickname: String, email: String, password: String)
}

class SignUpInteraction @Inject constructor(private val repo: AuthRepository): SignUpUseCase {
    override suspend fun execute(nickname: String, email: String, password: String) {
        repo.signup(nickname, email, password)
    }

}