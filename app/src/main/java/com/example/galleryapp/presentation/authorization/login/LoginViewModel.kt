package com.example.galleryapp.presentation.authorization.login

import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
//        loginUseCase.getCurrentUser()?.let { user ->
//            setState { copy(AuthScreenContract.AuthState.Success(u)) }
//        } ?: {
            setState {
                copy(AuthScreenContract.AuthState.Loading)
            }
            launch(
                request = {
                    loginUseCase.execute(email, password)
                },
                onSuccess = {result ->
                    setState { copy(AuthScreenContract.AuthState.Success(result?.user?.uid)) }
                },
                onError = {e ->
                    setState { copy(AuthScreenContract.AuthState.Failure(e)) }
                }
            )
//        }
    }

}