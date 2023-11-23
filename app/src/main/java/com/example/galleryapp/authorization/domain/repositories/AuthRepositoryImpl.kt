package com.example.galleryapp.authorization.domain.repositories

import android.util.Log
import com.example.galleryapp.authorization.domain.repositories.AuthRepostitory
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
//    private val firebaseDatabase: FirebaseDatabase
): AuthRepostitory {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun login(email: String, password: String){
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
    ) {
//       firebaseAuth.createUserWithEmailAndPassword(email, password)
//           .addOnCompleteListener {task ->
//               if (task.isSuccessful) {
//                   Log.d("he", "signed up")
//                   firebaseAuth.currentUser?.let {
//                       val uid = it.uid
//                       firebaseDatabase.getReference("Users").child(uid).setValue(User(uid, nickname, email))
//                   }
//               }
//           }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}