package com.example.galleryapp.presentation.authorization.signup

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.data.useCases.authorization.SignUpUseCase
import com.example.galleryapp.presentation.authorization.login.AuthScreenContract
import com.example.galleryapp.presentation.base.BaseViewModel
import com.example.galleryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    val signUpUseCase: SignUpUseCase
) :
    BaseViewModel<AuthScreenContract.State, AuthScreenContract.AuthEvent, AuthScreenContract.AuthEffect>() {
    override fun createInitialState(): AuthScreenContract.State {
        return AuthScreenContract.State(AuthScreenContract.AuthState.Idle)
    }

    override fun handleEvent(event: AuthScreenContract.AuthEvent) {
        when (event) {
            is AuthScreenContract.AuthEvent.OnAuthButtonClick -> {
                event.nickname?.let {
                    signUp(it, event.email, event.password)
                } ?: signUp("", event.email, event.password)
            }

            is AuthScreenContract.AuthEvent.OnSignUpClick -> {
                setEffect { AuthScreenContract.AuthEffect.NavigateToAnotherAuthMethod }
            }

            is AuthScreenContract.AuthEvent.OnBackIconClick -> {
                setEffect { AuthScreenContract.AuthEffect.NavigateBack }
            }
        }
    }

    private fun signUp(nickname: String, email: String, password: String) {
        setState { copy(AuthScreenContract.AuthState.Loading) }
        viewModelScope.launch {
            when(val result = signUpUseCase.execute(nickname, email, password)) {
                is Resource.Success -> {
                    val uid = result.data.user?.uid
                    uid?.let {
                        setState { copy(AuthScreenContract.AuthState.Success(it)) }
                    }
                }
                is Resource.Error -> {
                    setEffect {
                        AuthScreenContract.AuthEffect.ShowToast(
                            result.message ?: "Couldn't authorize"
                        )
                    }
                }
            }
        }
    }
}