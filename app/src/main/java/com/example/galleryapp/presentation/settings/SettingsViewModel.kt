package com.example.galleryapp.presentation.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.data.useCases.authorization.LogOutUseCase
import com.example.galleryapp.utils.enums.Languages
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesUtils: PreferencesUtils,
    private val logOutUseCase: LogOutUseCase,
    private val currentUser: CurrentUserUseCase
) : ViewModel() {

    fun saveLanguage(language: String) {
        preferencesUtils.saveString(Preferences.LANGUAGE, language)
    }

    fun getCurrentLanguage(): String {
        return preferencesUtils.getString(Preferences.LANGUAGE, Languages.EN.id)
    }

    fun loggedIn(): Boolean {
        return currentUser.getCurrentUser() != null
    }

    fun logOut() {
        viewModelScope.launch {
            logOutUseCase.execute()
        }
        preferencesUtils.saveBoolean(Preferences.AUTHORIZED, false)
    }
}