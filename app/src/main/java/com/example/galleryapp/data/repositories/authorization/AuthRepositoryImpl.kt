package com.example.galleryapp.data.repositories.authorization

import com.example.galleryapp.data.models.User
import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
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
        if (currentUser != null) {
            val user = User(currentUser?.uid, nickname, email)
            currentUser?.uid?.let { firebaseDB.child(it).setValue(user) }?.await()
        }
        return result
    }

    override suspend fun getUserInfo(onResult: (Resource<User>) -> Unit) {
        currentUser?.uid?.let { uid ->
            firebaseDB.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        try {
                            val userNickname =
                                snapshot.child("nickname").getValue(String::class.java)
                            val userEmail = snapshot.child("email").getValue(String::class.java)

                            val user = User(uid, userNickname, userEmail)
                            onResult.invoke(
                                Resource.Success(user)
                            )
                        } catch (e: Exception) {
                            onResult.invoke(
                                Resource.Failure(e.message)
                            )
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onResult.invoke(
                        Resource.Failure(error.message)
                    )
                }

            })
        }
    }

    override fun logout() {
        firebaseAuth.signOut()
    }
}