package com.example.galleryapp.data.repositories.authorization

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(
        email: String,
        password: String
    ) {
        val response = firebaseAuth.signInWithEmailAndPassword(email, password).await()

//        Log.d("reposnse", "${response.isSuccessful} erekldvadv;an;a")
//        return if (response.isSuccessful) {
//            response.result
//        } else {
//            throw Exception(response.exception)
//        }
    }

    override suspend fun signup(
        nickname: String,
        email: String,
        password: String
    ): AuthResult? {
        return firebaseAuth.createUserWithEmailAndPassword(email, password).await()
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}