package com.example.galleryapp.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.data.repositories.AuthRepostitory
import kotlinx.coroutines.launch

class LoginViewModel(val repo: AuthRepostitory): ViewModel() {
    fun login(email: String, password: String) {
        Log.d("he", "hello")
        viewModelScope.launch {
            repo.login(email, password)
        }
    }
}