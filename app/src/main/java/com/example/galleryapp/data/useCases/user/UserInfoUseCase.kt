package com.example.galleryapp.data.useCases.user

import com.example.galleryapp.data.models.User
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserRepository
import com.example.galleryapp.utils.Resource
import javax.inject.Inject

interface UserInfoUseCase {

    suspend fun execute(uid: String, onResult: (Resource<User>) -> Unit)
}

class GetUserInfoInteraction @Inject constructor(
    private val userRepo: FRDBUserRepository
): UserInfoUseCase {
    override suspend fun execute(uid: String, onResult: (Resource<User>) -> Unit) {
        userRepo.getUserInfo(uid, onResult)
    }

}