package com.example.galleryapp.data.repositories.authorization

import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String)
    suspend fun signup(nickname: String, email: String, password: String)
    fun logout()
}