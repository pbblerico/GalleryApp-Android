package com.example.galleryapp.data.di.db

import com.example.galleryapp.data.repositories.firebaseRD.FRBDUser
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserRepository
import com.example.galleryapp.data.repositories.firebaseRD.FRDBUserRepositoryImpl
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = FirebaseDatabase.getInstance()

    @Provides
    @Singleton
    fun provideDatabaseReference(database: FirebaseDatabase): DatabaseReference = database.getReference(FRBDUser.ROOT.value)

    @Provides
    @Singleton
    fun provideFirebaseStorageRef(): StorageReference = Firebase.storage.reference

    @Provides
    @Singleton
    fun provideFRBDUserRepository(dbRef: DatabaseReference): FRDBUserRepository = FRDBUserRepositoryImpl(dbRef)

}