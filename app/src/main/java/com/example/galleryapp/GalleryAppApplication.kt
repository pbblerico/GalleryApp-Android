package com.example.galleryapp

import android.app.Application
import com.example.galleryapp.data.modules.firebaseModule
import com.example.galleryapp.data.modules.repositoryModule
import com.example.galleryapp.data.modules.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GalleryAppApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@GalleryAppApplication)
            modules(
                firebaseModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}