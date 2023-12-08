package com.example.galleryapp.presentation.authorization.signup

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.data.useCases.authorization.SignUpUseCase
import com.example.galleryapp.presentation.authorization.login.AuthContract
import com.example.galleryapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase,
    private val getCurrentUser: CurrentUserUseCase,
    private val preferencesUtils: PreferencesUtils
) :
    BaseViewModel<AuthContract.AuthState, AuthContract.AuthEvent, AuthContract.AuthEffect>() {
    override fun createInitialState(): AuthContract.AuthState {
        return AuthContract.AuthState.Idle
    }

    override fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.OnAuthButtonClick -> {
                event.nickname?.let {
                    if (!validateInputs(it, event.email, event.password)) {
                        setState(AuthContract.AuthState.Idle)
                        setEffect(AuthContract.AuthEffect.ShowToast("Please, fill all fields"))
                    } else {
                        signUp(it, event.email, event.password)
                    }
                }
            }

            is AuthContract.AuthEvent.OnSignUpClick -> {
                setEffect(AuthContract.AuthEffect.NavigateToAnotherAuthMethod)
            }

            is AuthContract.AuthEvent.OnBackIconClick -> {
                setEffect(AuthContract.AuthEffect.NavigateBack)
            }
        }
    }

    private fun validateInputs(nickname: String, email: String, password: String): Boolean {
        return !(nickname.isBlank() || email.isBlank() || password.isBlank())
    }

    fun saveLogs() {
        preferencesUtils.saveBoolean(Preferences.AUTHORIZED, true)
    }


    private fun signUp(nickname: String, email: String, password: String) {
        setState(AuthContract.AuthState.Loading)
        viewModelScope.launch {
            try {
                signUpUseCase.execute(nickname, email, password) { result ->
                    when (result) {
                        is Resource.Success -> {
                            setState(AuthContract.AuthState.Success("success"))
                            setEffect(AuthContract.AuthEffect.NavigateToHome)
                        }

                        is Resource.Failure -> {
                            setState(AuthContract.AuthState.Failure(result.message))
                        }
                    }
                }
            } catch (e: Exception) {
                setEffect(AuthContract.AuthEffect.ShowToast(e.message.orEmpty()))
                setState(AuthContract.AuthState.Idle)
            }

        }
    }
}