package com.example.galleryapp.data.repositories.firebaseRD

import com.google.firebase.database.FirebaseDatabase
import javax.inject.Inject

class FirebaseRDRepositoryImpl @Inject constructor(
    private val database: FirebaseDatabase
): FirebaseRDRepository {



}