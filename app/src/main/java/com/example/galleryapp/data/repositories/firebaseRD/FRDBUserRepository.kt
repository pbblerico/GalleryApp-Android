package com.example.galleryapp.data.repositories.firebaseRD

import com.example.galleryapp.data.models.User
import com.example.galleryapp.utils.Resource

interface FRDBUserRepository {
    suspend fun createUser(user: User, onResult: (Resource<String>) -> Unit)

    suspend fun getUserInfo(uid: String, onResult:(Resource<User>) -> Unit)

    suspend fun updateNickname(id: String, nickname: String, onResult: (Resource<String>) -> Unit)

    suspend fun updateAvatar(id: String, avatarUrl: String, onResult: (Resource<String>) -> Unit)
}