package com.example.galleryapp.presentation.authorization.signup

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.useCases.authorization.SignUpUseCase
import com.example.galleryapp.presentation.authorization.login.AuthContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) :
    BaseViewModel<AuthContract.AuthState, AuthContract.AuthEvent, AuthContract.AuthEffect>() {
    override fun createInitialState(): AuthContract.AuthState {
        return AuthContract.AuthState.Idle
    }

    override fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.OnAuthButtonClick -> {
                event.nickname?.let {
                    signUp(it, event.email, event.password)
                } ?: signUp("", event.email, event.password)
            }

            is AuthContract.AuthEvent.OnSignUpClick -> {
                setEffect ( AuthContract.AuthEffect.NavigateToAnotherAuthMethod )
            }

            is AuthContract.AuthEvent.OnBackIconClick -> {
                setEffect ( AuthContract.AuthEffect.NavigateBack )
            }
        }
    }

    private fun signUp(nickname: String, email: String, password: String) {
        setState (AuthContract.AuthState.Loading)
        viewModelScope.launch {
            try {
                val result = signUpUseCase.execute(nickname, email, password)
                val uid = result?.user?.uid
                uid?.let {
                    setState (AuthContract.AuthState.Success(it))
                }
            } catch (e: Exception) {
                Log.e("auth_exception", e.message ?: "unknown error")
            }
        }
    }
}