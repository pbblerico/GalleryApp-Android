package com.example.galleryapp.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class AuthRepositoryImpl(private val firebaseAuth: FirebaseAuth): AuthRepostitory {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String){
        Log.d("login", "here")
       firebaseAuth.signInWithEmailAndPassword(email, password)
           .addOnCompleteListener { task ->
               if (task.isSuccessful) {
                   Log.d("he",  "$currentUser")
               }
           }
    }

    override suspend fun signup(
        email: String,
        password: String,
        nickname: String
    ): FirebaseUser {
        TODO("Not yet implemented")
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}