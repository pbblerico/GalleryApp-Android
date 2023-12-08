package com.example.galleryapp.data.di.db

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object PreferencesModule {

    @Provides
    @Singleton
    fun providePreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(Preferences.APP.name, AppCompatActivity.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providePreferencesUtils(preferences: SharedPreferences): PreferencesUtils {
        return PreferencesUtils(preferences)
    }
}