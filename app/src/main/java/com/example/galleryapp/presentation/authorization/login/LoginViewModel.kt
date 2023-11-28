package com.example.galleryapp.presentation.authorization.login

import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import com.example.galleryapp.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val login: LoginUseCase
): BaseViewModel<LoginScreenContract.State, LoginScreenContract.LoginEvent, LoginScreenContract.LoginEffect>() {
    override fun createInitialState(): LoginScreenContract.State {
        return LoginScreenContract.State(LoginScreenContract.LoginState.Idle)
    }

    override fun handleEvent(event: LoginScreenContract.LoginEvent) {
        when(event) {
            is LoginScreenContract.LoginEvent.OnLoginButtonClick -> {
                setState {
                    copy(LoginScreenContract.LoginState.Loading)
                }
            }
            is LoginScreenContract.LoginEvent.OnSignUpClick -> {
                setEffect { LoginScreenContract.LoginEffect.NavigateToSignUp }
            }
        }
    }

}