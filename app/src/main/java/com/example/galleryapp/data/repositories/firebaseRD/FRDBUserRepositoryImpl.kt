package com.example.galleryapp.data.repositories.firebaseRD

import com.example.galleryapp.data.models.User
import com.example.galleryapp.utils.Resource
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject

class FRDBUserRepositoryImpl @Inject constructor(
    private val databaseRef: DatabaseReference
) : FRDBUserRepository {
    override suspend fun createUser(
        user: User,
        onResult: (Resource<String>) -> Unit
    ) {
        user.id?.let {
            databaseRef
                .child(it)
                .child(FRBDUser.USER_INFO.value)
                .setValue(user)
                .addOnSuccessListener {
                    onResult.invoke(
                        Resource.Success("User created")
                    )
                }
                .addOnFailureListener { e ->
                    onResult.invoke(
                        Resource.Failure(e.message)
                    )
                }
        }
    }

    override suspend fun getUserInfo(uid: String, onResult: (Resource<User>) -> Unit) {
        databaseRef
            .child(uid)
            .child(FRBDUser.USER_INFO.value)
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(User::class.java)

                    onResult.invoke(
                        Resource.Success(user)
                    )
//                    if (snapshot.exists()) {
//                        try {
//                            val userNickname =
//                                snapshot.child("nickname").getValue(String::class.java)
//                            val userEmail = snapshot.child("email").getValue(String::class.java)
//
//                            val user = User(uid, userNickname, userEmail)
//                            onResult.invoke(
//                                Resource.Success(user)
//                            )
//                        } catch (e: Exception) {
//                            onResult.invoke(
//                                Resource.Failure(e.message)
//                            )
//                        }
//                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onResult.invoke(
                        Resource.Failure(error.message)
                    )
                }

            })
    }

    override suspend fun updateNickname(
        id: String,
        nickname: String,
        onResult: (Resource<String>) -> Unit
    ) {
        databaseRef
            .child(id)
            .child(FRBDUser.USER_INFO.value)
            .child(FRBDUser.NICKNAME.value)
            .setValue(nickname)
            .addOnSuccessListener {
                onResult.invoke(
                    Resource.Success("nickname updated")
                )
            }
            .addOnFailureListener { e ->
                onResult.invoke(
                    Resource.Failure(e.message)
                )
            }
    }

    override suspend fun updateAvatar(
        id: String,
        avatarUrl: String,
        onResult: (Resource<String>) -> Unit
    ) {
        databaseRef
            .child(id)
            .child(FRBDUser.USER_INFO.value)
            .child(FRBDUser.AVATAR.value)
            .setValue(avatarUrl)
            .addOnSuccessListener {
                onResult.invoke(
                    Resource.Success("avatar updated")
                )
            }
            .addOnFailureListener { e ->
                onResult.invoke(
                    Resource.Failure(e.message)
                )
            }
    }


}

enum class FRBDUser(val value: String) {
    ROOT("users"),
    USER_INFO("user_info"),
    NICKNAME("nickname"),
    EMAIL("email"),
    AVATAR("avatar"),
    SAVED("saved"),
}