package com.example.galleryapp.authorization.presentation

import com.example.galleryapp.shared.base.UiEffect
import com.example.galleryapp.shared.base.UiEvent
import com.example.galleryapp.shared.base.UiState
import com.google.firebase.auth.FirebaseUser

//class LoginScreenContract {
//}

class LoginScreenContract {

    data class State(
        val loginState: LoginState
    ): UiState
    sealed class LoginState  {
        object Idle: LoginState()
        object Loading: LoginState()
        data class LoginSucces(val user: FirebaseUser): LoginState()
        data class LoginFailure(val message: String?): LoginState()

    }

    sealed class LoginEvent: UiEvent {
        data class OnLoginButtonClick(val email: String, val password: String): LoginEvent()
    }

    sealed class LoginEffect: UiEffect {
        data class Login(val email: String, val password: String): LoginEffect()
    }
}