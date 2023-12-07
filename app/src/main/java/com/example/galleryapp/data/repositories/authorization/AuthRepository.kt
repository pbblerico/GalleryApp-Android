package com.example.galleryapp.data.repositories.authorization

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?

    //    fun observeAuthState(): Flow<FirebaseUser?>
    suspend fun login(email: String, password: String)

    suspend fun signup(nickname: String, email: String, password: String): AuthResult?
    fun logout()
}