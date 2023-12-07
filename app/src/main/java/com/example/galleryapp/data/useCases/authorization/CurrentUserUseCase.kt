package com.example.galleryapp.data.useCases.authorization

import com.example.galleryapp.data.models.User
import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface CurrentUserUseCase {
    fun getCurrentUser(): FirebaseUser?

    suspend fun getUserInfo(onResult: (Resource<User>) -> Unit)
//    fun observeAuthState(): Flow<FirebaseUser?>
}

class CurrentUserInteraction @Inject constructor(val repo: AuthRepository): CurrentUserUseCase {
    override fun getCurrentUser(): FirebaseUser? {
        return repo.currentUser
    }

    override suspend fun getUserInfo(onResult: (Resource<User>) -> Unit) {
        repo.getUserInfo(onResult)
    }

}