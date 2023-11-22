package com.example.galleryapp.data.repositories

import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.FirebaseUser

interface AuthRepostitory {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String)
    suspend fun signup(email: String, password: String, nickname: String): FirebaseUser
    fun logout()
}