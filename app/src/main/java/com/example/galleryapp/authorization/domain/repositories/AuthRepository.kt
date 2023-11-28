package com.example.galleryapp.authorization.domain.repositories

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String)
    suspend fun signup(nickname: String, email: String, password: String)
    fun logout()
}