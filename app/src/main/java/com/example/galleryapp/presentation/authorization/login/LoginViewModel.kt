package com.example.galleryapp.presentation.authorization.login

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUser: CurrentUserUseCase
) : BaseViewModel<AuthContract.AuthState, AuthContract.AuthEvent, AuthContract.AuthEffect>() {
    override fun createInitialState(): AuthContract.AuthState {
        return AuthContract.AuthState.Idle
    }

    override fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.OnAuthButtonClick -> {
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

            loginUseCase.execute(email, password)
            if(getCurrentUser.getCurrentUser() != null) {
                setState(AuthContract.AuthState.Success("success"))
                setEffect(AuthContract.AuthEffect.NavigateToHome)
            } else {
                setState(AuthContract.AuthState.Failure("error"))
            }

        }
    }

}