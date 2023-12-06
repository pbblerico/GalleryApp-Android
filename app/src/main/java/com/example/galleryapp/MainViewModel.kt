package com.example.galleryapp

import androidx.lifecycle.ViewModel
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class MainViewModel  @Inject constructor(private val getCurrentUser: CurrentUserUseCase): ViewModel(){

//    private var _currentUseState:
//    val currentUseState: LiveData<FirebaseUser?> = _currentUseState

    private var _userStateFlow: Flow<FirebaseUser?>? = null
    val userStateFlow= _userStateFlow

//    fun observeAuthState() {
//        _userStateFlow = getCurrentUser.observeAuthState()
//    }

//    fun getFirebaseUser() {
//       _currentUseState.value = getCurrentUser.getCurrentUser()
//    }

}