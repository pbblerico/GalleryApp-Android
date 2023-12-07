package com.example.galleryapp.presentation.authorization.login

import com.example.galleryapp.shared.base.UiEffect
import com.example.galleryapp.shared.base.UiEvent
import com.example.galleryapp.shared.base.UiState


class AuthContract {
    sealed class AuthState: UiState  {
        data object Idle: AuthState()
        data object Loading: AuthState()
        data class Success(val uid: String?): AuthState()
        data class Failure(val message: String?): AuthState()

    }

    sealed class AuthEvent: UiEvent {
        data class OnAuthButtonClick(val email: String, val password: String, val nickname: String? = null): AuthEvent()
        data object OnSignUpClick: AuthEvent()
        data object OnBackIconClick: AuthEvent()
    }

    sealed class AuthEffect: UiEffect {
        data object NavigateToAnotherAuthMethod: AuthEffect()
        data object NavigateBack: AuthEffect()
        data class ShowToast(val message: String): AuthEffect()
    }
}