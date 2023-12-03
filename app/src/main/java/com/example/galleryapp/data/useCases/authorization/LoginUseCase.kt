package com.example.galleryapp.data.useCases.authorization

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject


//todo (change use case)
interface LoginUseCase {
    suspend fun execute(email: String, password: String): AuthResult?

    fun getCurrentUser(): FirebaseUser?
}

class LoginInteraction @Inject constructor(private val repo: AuthRepository): LoginUseCase {
    override suspend fun execute(email: String, password: String): AuthResult? {
        return repo.login(email, password)
    }

    override fun getCurrentUser(): FirebaseUser? {
        return repo.currentUser
    }


}