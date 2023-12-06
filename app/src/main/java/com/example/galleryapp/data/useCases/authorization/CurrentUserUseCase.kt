package com.example.galleryapp.data.useCases.authorization

import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

interface CurrentUserUseCase {
    fun getCurrentUser(): FirebaseUser?

//    fun observeAuthState(): Flow<FirebaseUser?>
}

class CurrentUserInteraction @Inject constructor(val repo: AuthRepository): CurrentUserUseCase {
    override fun getCurrentUser(): FirebaseUser? {
        return repo.currentUser
    }

//    override fun observeAuthState(): Flow<FirebaseUser?> {
//        return repo.observeAuthState()
//    }


}