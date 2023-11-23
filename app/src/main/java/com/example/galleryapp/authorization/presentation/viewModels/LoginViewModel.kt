package com.example.galleryapp.authorization.presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.authorization.domain.repositories.AuthRepostitory
import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(val repo: AuthRepostitory): ViewModel() {

    private val _loginState = MutableLiveData<Resource<FirebaseUser>>()
    val loginState: LiveData<Resource<FirebaseUser>> = _loginState
    fun login(email: String, password: String) {
        Log.d("he", "hello")
        viewModelScope.launch {
            repo.login(email, password)
        }
    }

    fun signUp(name: String, email: String, password: String) {
        viewModelScope.launch {
            repo.signup(email, password, name)
        }
    }
}