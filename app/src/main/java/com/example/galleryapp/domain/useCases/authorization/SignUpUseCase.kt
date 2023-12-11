package com.example.galleryapp.domain.useCases.authorization

import com.example.galleryapp.data.models.User
import com.example.galleryapp.data.repositories.authorization.AuthRepository
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserRepository
import com.example.galleryapp.utils.Resource
import javax.inject.Inject

interface SignUpUseCase {
    suspend fun execute(
        nickname: String,
        email: String,
        password: String,
        onResult: (Resource<String>) -> Unit
    )
}

class SignUpInteraction @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: FRDBUserRepository
) : SignUpUseCase {

    override suspend fun execute(
        nickname: String,
        email: String,
        password: String,
        onResult: (Resource<String>) -> Unit
    ) {

        val fbUser = authRepository.signup(email, password)?.user

        if (fbUser != null) {
            val user = User(fbUser.uid, nickname, email, null)
            userRepository.createUser(user, onResult)
        }
    }

}