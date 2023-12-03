package com.example.galleryapp.data.repositories.authorization

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
//    suspend fun login(email: String, password: String): Resource<AuthResult>
    suspend fun login(email: String, password: String): AuthResult?

    suspend fun signup(nickname: String, email: String, password: String): AuthResult?
//    suspend fun signup(nickname: String, email: String, password: String): Resource<AuthResult>
    fun logout()
}