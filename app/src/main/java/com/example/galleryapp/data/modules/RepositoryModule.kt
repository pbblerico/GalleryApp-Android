package com.example.galleryapp.data.modules

import com.example.galleryapp.data.repositories.AuthRepositoryImpl
import com.example.galleryapp.data.repositories.AuthRepostitory
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module

val repositoryModule = module {
    single {
        getAuthRepository(firebaseAuth = get())
    }
}

private fun getAuthRepository(firebaseAuth: FirebaseAuth): AuthRepostitory = AuthRepositoryImpl(firebaseAuth)
