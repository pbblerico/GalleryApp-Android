package com.example.galleryapp.data.useCases.authorization

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.AuthResult
import javax.inject.Inject

interface SignUpUseCase {
    suspend fun execute(nickname: String, email: String, password: String): Resource<AuthResult>
}

class SignUpInteraction @Inject constructor(private val repo: AuthRepository): SignUpUseCase {
    override suspend fun execute(nickname: String, email: String, password: String): Resource<AuthResult> {
        return repo.signup(nickname, email, password)
    }

}