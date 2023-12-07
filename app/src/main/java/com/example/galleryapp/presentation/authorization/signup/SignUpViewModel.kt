package com.example.galleryapp.presentation.authorization.signup

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.data.useCases.authorization.SignUpUseCase
import com.example.galleryapp.presentation.authorization.login.AuthContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getCurrentUser: CurrentUserUseCase
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
            signUpUseCase.execute(nickname, email, password)
            if(getCurrentUser.getCurrentUser() != null) {
                setState(AuthContract.AuthState.Success("success"))
                setEffect(AuthContract.AuthEffect.NavigateToHome)
            } else {
                setState(AuthContract.AuthState.Failure("error"))
            }
        }
    }
}