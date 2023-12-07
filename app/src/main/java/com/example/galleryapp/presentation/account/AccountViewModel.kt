package com.example.galleryapp.presentation.account

import androidx.lifecycle.ViewModel
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val currentUser: CurrentUserUseCase
): ViewModel() {

    fun getCurrentUser(): FirebaseUser? {
        return currentUser.getCurrentUser()
    }
}