package com.example.galleryapp.data.useCases.user

import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserRepository
import com.example.galleryapp.utils.Resource
import javax.inject.Inject

interface ManageUserUseCase {
    suspend fun updateNickname(uid: String, nickname: String, onResult: (Resource<String>) -> Unit)

    suspend fun updateAvatar(uid: String, avatar: String, onResult: (Resource<String>) -> Unit)

}

class ManageUserInteraction @Inject constructor(
    private val userRepo: FRDBUserRepository
): ManageUserUseCase {
    override suspend fun updateNickname(
        uid: String,
        nickname: String,
        onResult: (Resource<String>) -> Unit
    ) {
        userRepo.updateNickname(uid, nickname, onResult)
    }

    override suspend fun updateAvatar(
        uid: String,
        avatar: String,
        onResult: (Resource<String>) -> Unit
    ) {
        userRepo.updateAvatar(uid, avatar, onResult)
    }

}