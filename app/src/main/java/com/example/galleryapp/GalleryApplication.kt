package com.example.galleryapp

import android.app.Application
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GalleryApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Lingver.init(this, "en")
    }
}