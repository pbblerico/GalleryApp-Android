package com.example.galleryapp.authorization.presentation.viewModels

import com.example.galleryapp.authorization.domain.useCases.LoginUseCase
import com.example.galleryapp.authorization.presentation.LoginScreenContract
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
                setEffect { LoginScreenContract.LoginEffect.Login(event.email, event.password) }
            }
        }
    }

}