package com.example.galleryapp.presentation.authorization.login

import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.useCases.authorization.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
) : BaseViewModel<AuthContract.State, AuthContract.AuthEvent, AuthContract.AuthEffect>() {
    override fun createInitialState(): AuthContract.State {
        return AuthContract.State(AuthContract.AuthState.Idle)
    }

    override fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.OnAuthButtonClick -> {
                login(event.email, event.password)
            }

            is AuthContract.AuthEvent.OnSignUpClick -> {
                setEffect { AuthContract.AuthEffect.NavigateToAnotherAuthMethod }
            }

            is AuthContract.AuthEvent.OnBackIconClick -> {
                setEffect { AuthContract.AuthEffect.NavigateBack }
            }
        }
    }

    private fun login(email: String, password: String) {
//        loginUseCase.getCurrentUser()?.let { user ->
//            setState { copy(AuthScreenContract.AuthState.Success(u)) }
//        } ?: {
            setState {
                copy(AuthContract.AuthState.Loading)
            }
            launch(
                request = {
                    loginUseCase.execute(email, password)
                },
                onSuccess = {result ->
                    setState { copy(AuthContract.AuthState.Success(result?.user?.uid)) }
                },
                onError = {e ->
                    setState { copy(AuthContract.AuthState.Failure(e)) }
                }
            )
//        }
    }

}