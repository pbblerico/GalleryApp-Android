package com.example.galleryapp.presentation.authorization.login

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import com.example.galleryapp.domain.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.domain.useCases.authorization.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val getCurrentUser: CurrentUserUseCase,
    private val preferencesUtils: PreferencesUtils
) : BaseViewModel<AuthContract.AuthState, AuthContract.AuthEvent, AuthContract.AuthEffect>() {
    override fun createInitialState(): AuthContract.AuthState {
        return AuthContract.AuthState.Idle
    }

    override fun handleEvent(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.OnAuthButtonClick -> {
                if(!validateInputs(event.email, event.password)) {
                    setEffect(AuthContract.AuthEffect.ShowToast("Please, fill all fields"))
                    setState(AuthContract.AuthState.Idle)
                } else {
                    login(event.email, event.password)
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

    private fun validateInputs(email: String, password: String): Boolean {
        return !(email.isBlank() || password.isBlank())
    }


    fun saveLogs() {
        preferencesUtils.saveBoolean(Preferences.AUTHORIZED, true)
    }

    private fun login(email: String, password: String) {
        setState(AuthContract.AuthState.Loading)

        viewModelScope.launch {
            try {
                loginUseCase.execute(email, password)

                setState(AuthContract.AuthState.Success("success"))
                setEffect(AuthContract.AuthEffect.NavigateToHome)
            } catch (e: Exception) {
                setState(AuthContract.AuthState.Idle)
                setEffect(AuthContract.AuthEffect.ShowToast(e.message.orEmpty()))
            }
        }
    }

}