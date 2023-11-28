package com.example.galleryapp.presentation.authorization.signup

import androidx.lifecycle.ViewModel
import com.example.galleryapp.data.repositories.authorization.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(val repo: AuthRepository): ViewModel() {
    //todo(implementation of view model)
}