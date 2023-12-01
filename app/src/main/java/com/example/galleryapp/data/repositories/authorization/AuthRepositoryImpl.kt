package com.example.galleryapp.data.repositories.authorization

import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
//    private val preferencesUtils: PreferencesUtils
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(
        email: String,
        password: String
    ): Resource<AuthResult> {
        val response = firebaseAuth.signInWithEmailAndPassword(email, password)
        return if (response.isSuccessful) {
            Resource.Success(response.result)
        } else
            Resource.Error(response.exception?.message ?: "Unknown error")
    }

    private fun getToken() {

    }

    override suspend fun signup(
        nickname: String,
        email: String,
        password: String
    ): Resource<AuthResult> {
        val response = firebaseAuth.createUserWithEmailAndPassword(email, password)
        return if(response.isSuccessful) {
            Resource.Success(response.result)
        } else {
            Resource.Error(response.exception?.message ?: "Unknown error")
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}