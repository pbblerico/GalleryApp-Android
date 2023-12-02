package com.example.galleryapp.presentation.authorization.login

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : BaseViewModel<AuthScreenContract.State, AuthScreenContract.AuthEvent, AuthScreenContract.AuthEffect>() {
    override fun createInitialState(): AuthScreenContract.State {
        return AuthScreenContract.State(AuthScreenContract.AuthState.Idle)
    }

    override fun handleEvent(event: AuthScreenContract.AuthEvent) {
        when (event) {
            is AuthScreenContract.AuthEvent.OnAuthButtonClick -> {
                login(event.email, event.password)
            }

            is AuthScreenContract.AuthEvent.OnSignUpClick -> {
                setEffect { AuthScreenContract.AuthEffect.NavigateToAnotherAuthMethod }
            }

            is AuthScreenContract.AuthEvent.OnBackIconClick -> {
                setEffect { AuthScreenContract.AuthEffect.NavigateBack }
            }
        }
    }

    private fun login(email: String, password: String) {
        loginUseCase.getCurrentUser()?.let { user ->
            setState { copy(AuthScreenContract.AuthState.Success(user.uid)) }
        } ?: {
            setState {
                copy(AuthScreenContract.AuthState.Loading)
            }
            viewModelScope.launch {
                when (val result = loginUseCase.execute(email, password)) {
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

}