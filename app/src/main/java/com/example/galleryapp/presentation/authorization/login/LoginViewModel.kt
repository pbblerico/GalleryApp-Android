package com.example.galleryapp.presentation.authorization.login

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<AuthContract.AuthState, AuthContract.AuthEvent, AuthContract.AuthEffect>() {
    override fun createInitialState(): AuthContract.AuthState {
        return AuthContract.AuthState.Idle
    }

    override fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.OnAuthButtonClick -> {
                Log.d("login_methid", "login_met")
                login(event.email, event.password)
            }

            is AuthContract.AuthEvent.OnSignUpClick -> {
                setEffect(AuthContract.AuthEffect.NavigateToAnotherAuthMethod)
            }

            is AuthContract.AuthEvent.OnBackIconClick -> {
                setEffect(AuthContract.AuthEffect.NavigateBack)
            }
        }
    }

    private fun login(email: String, password: String) {
        setState(AuthContract.AuthState.Loading)
        viewModelScope.launch {
            try {
                val response = loginUseCase.execute(email, password)
                Log.d("login_methid", "login try")
                setState(AuthContract.AuthState.Success(response?.user?.uid))
            } catch (e: Exception) {
                setState(AuthContract.AuthState.Failure(e.message))
            }
        }
//            launch(
//                request = {
//                    loginUseCase.execute(email, password)
//                },
//                onSuccess = {result ->
//                    setState(AuthContract.AuthState.Success(result?.user?.uid))
//                },
//                onError = {e ->
//                    setState(AuthContract.AuthState.Failure(e))
//                }
//            )
    }

}