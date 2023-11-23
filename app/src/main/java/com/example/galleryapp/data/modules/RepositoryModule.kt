package com.example.galleryapp.data.modules

import com.example.galleryapp.authorization.domain.repositories.AuthRepositoryImpl
import com.example.galleryapp.authorization.domain.repositories.AuthRepostitory
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

//val repositoryModule = module {
////    single {
////        getAuthRepository(firebaseAuth = get(), firebaseDatabase = get())
////    }
//}

//private fun getAuthRepository(firebaseAuth: FirebaseAuth, firebaseDatabase: FirebaseDatabase): AuthRepostitory = AuthRepositoryImpl(firebaseAuth, firebaseDatabase)

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepostitory = AuthRepositoryImpl(firebaseAuth)
}