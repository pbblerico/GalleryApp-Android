package com.example.galleryapp.data.repositories.authorization

import android.util.Log
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(
        email: String,
        password: String
    ): AuthResult? {
        val response = firebaseAuth.signInWithEmailAndPassword(email, password)
        Log.d("reposnse", "${response.result.user?.uid} erekldvadv;an;a")
        return if (response.isSuccessful) {
            response.result
        } else {
            throw Exception(response.exception)
        }
    }

    override suspend fun signup(
        nickname: String,
        email: String,
        password: String
    ): AuthResult? {
        val response = firebaseAuth.createUserWithEmailAndPassword(email, password)
        return if(response.isSuccessful) {
            response.result
        } else {
            throw Exception(response.exception)
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}