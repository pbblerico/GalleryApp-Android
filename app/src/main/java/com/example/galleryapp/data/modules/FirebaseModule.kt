package com.example.galleryapp.data.modules

import com.example.galleryapp.data.repositories.AuthRepositoryImpl
import com.example.galleryapp.data.repositories.AuthRepostitory
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module


val firebaseModule = module {
    single {
        getFirebaseAuth()
    }
}
private fun getFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()
