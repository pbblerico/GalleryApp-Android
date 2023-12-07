package com.example.galleryapp

import androidx.lifecycle.ViewModel
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MainViewModel  @Inject constructor(private val preferencesUtils: PreferencesUtils): ViewModel(){

//    private var _currentUseState:
//    val currentUseState: LiveData<FirebaseUser?> = _currentUseState


    fun isAuthorized(): Boolean {
        return preferencesUtils.getBoolean(Preferences.AUTHORIZED, false)
    }


//    fun observeAuthState() {
//        _userStateFlow = getCurrentUser.observeAuthState()
//    }

//    fun getFirebaseUser() {
//       _currentUseState.value = getCurrentUser.getCurrentUser()
//    }

}