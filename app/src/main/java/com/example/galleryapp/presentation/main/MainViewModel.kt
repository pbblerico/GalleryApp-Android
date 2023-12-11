package com.example.galleryapp.presentation.main

import androidx.lifecycle.ViewModel
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel  @Inject constructor(private val preferencesUtils: PreferencesUtils): ViewModel(){
    fun isAuthorized(): Boolean {
        return preferencesUtils.getBoolean(Preferences.AUTHORIZED, false)
    }

}