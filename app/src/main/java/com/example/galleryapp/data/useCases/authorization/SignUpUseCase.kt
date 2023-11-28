package com.example.galleryapp.data.useCases.authorization

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import javax.inject.Inject

interface SignUpUseCase {
    suspend fun execute(nickname: String, email: String, password: String)
}

class SignUpInteraction @Inject constructor(private val repo: AuthRepository): SignUpUseCase {
    override suspend fun execute(nickname: String, email: String, password: String) {
        repo.signup(nickname, email, password)
    }

}