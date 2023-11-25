package com.example.galleryapp.authorization.presentation.viewModels

import androidx.lifecycle.ViewModel
import com.example.galleryapp.authorization.domain.repositories.AuthRepostitory
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(val repo: AuthRepostitory): ViewModel() {
}