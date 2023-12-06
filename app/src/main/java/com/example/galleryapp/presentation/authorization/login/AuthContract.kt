package com.example.galleryapp.presentation.authorization.login

import com.example.galleryapp.shared.base.UiEffect
import com.example.galleryapp.shared.base.UiEvent
import com.example.galleryapp.shared.base.UiState


class AuthContract {

    data class State(
        val authState: AuthState
    ): UiState
    sealed class AuthState  {
        object Idle: AuthState()
        object Loading: AuthState()
        data class Success(val uid: String?): AuthState()
        data class Failure(val message: String?): AuthState()

    }

    sealed class AuthEvent: UiEvent {
        data class OnAuthButtonClick(val email: String, val password: String, val nickname: String? = null): AuthEvent()
        object OnSignUpClick: AuthEvent()
        object OnBackIconClick: AuthEvent()
    }

    sealed class AuthEffect: UiEffect {
        object NavigateToAnotherAuthMethod: AuthEffect()
        object NavigateBack: AuthEffect()
        data class ShowToast(val message: String): AuthEffect()
    }
}