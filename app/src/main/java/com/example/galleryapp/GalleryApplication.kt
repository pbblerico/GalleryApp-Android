package com.example.galleryapp

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import com.example.galleryapp.utils.enums.Languages
import com.example.galleryapp.utils.enums.Theme
import com.yariksoffice.lingver.Lingver
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GalleryApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val sharedPreferences = applicationContext.getSharedPreferences(Preferences.APP.name, AppCompatActivity.MODE_PRIVATE)
        val preferencesUtils = PreferencesUtils(sharedPreferences)
        val initLanguage = preferencesUtils.getString(Preferences.LANGUAGE, Languages.EN.id)
        Lingver.init(this, initLanguage)

        val initTheme = preferencesUtils.getInt(Preferences.THEME, Theme.SYSTEM.system)
        AppCompatDelegate.setDefaultNightMode(initTheme)
    }
}