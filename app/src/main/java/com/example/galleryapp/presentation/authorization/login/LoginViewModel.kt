package com.example.galleryapp.presentation.authorization.login

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import com.example.galleryapp.shared.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
): BaseViewModel<LoginScreenContract.State, LoginScreenContract.LoginEvent, LoginScreenContract.LoginEffect>() {
    override fun createInitialState(): LoginScreenContract.State {
        return LoginScreenContract.State(LoginScreenContract.LoginState.Idle)
    }

    override fun handleEvent(event: LoginScreenContract.LoginEvent) {
        when(event) {
            is LoginScreenContract.LoginEvent.OnLoginButtonClick -> {
                login(event.email, event.password)
            }
            is LoginScreenContract.LoginEvent.OnSignUpClick -> {
                setEffect { LoginScreenContract.LoginEffect.NavigateToSignUp }
            }
            is LoginScreenContract.LoginEvent.OnBackIconClick -> {
                setEffect { LoginScreenContract.LoginEffect.NavigateBack }
            }
        }
    }

    private fun login(email: String, password: String) {
        setState {
            copy(LoginScreenContract.LoginState.Loading)
        }
        viewModelScope.launch {
            loginUseCase.execute(email, password)

        }
    }

}