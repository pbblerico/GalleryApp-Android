package com.example.galleryapp.data.repositories.authorization

import com.example.galleryapp.data.models.User
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseDB: DatabaseReference
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(
        email: String,
        password: String
    ) {
        firebaseAuth.signInWithEmailAndPassword(email, password).await()
    }

    override suspend fun signup(
        nickname: String,
        email: String,
        password: String
    ): AuthResult? {
        val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        if(currentUser != null) {
            val user = User(currentUser?.uid, nickname, email)
            currentUser?.uid?.let { firebaseDB.child(it).setValue(user) }?.await()
        }
        return result
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}