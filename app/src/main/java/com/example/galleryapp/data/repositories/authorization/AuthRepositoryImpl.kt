package com.example.galleryapp.data.repositories.authorization

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

//    override fun observeAuthState() = callbackFlow {
//        val authStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
//            val user = firebaseAuth.currentUser
//            trySend(user)
//        }
//
//        firebaseAuth.addAuthStateListener(authStateListener)
//
//        awaitClose {
//            firebaseAuth.removeAuthStateListener(authStateListener)
//        }
//    }

    override suspend fun login(
        email: String,
        password: String
    ): AuthResult? {
        val response = firebaseAuth.signInWithEmailAndPassword(email, password)
        return if (response.isSuccessful) {
            response.result
        } else {
            throw Exception(response.exception)
        }
    }

    private fun getToken() {

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