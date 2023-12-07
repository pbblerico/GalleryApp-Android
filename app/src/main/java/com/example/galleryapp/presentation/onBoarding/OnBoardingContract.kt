package com.example.galleryapp.presentation.onBoarding

import com.example.galleryapp.utils.UiEffect
import com.example.galleryapp.utils.UiEvent
import com.example.galleryapp.utils.UiState

class OnBoardingContract {
    sealed class OnBoardingState : UiState {
        data object Initial : OnBoardingState()
    }

    sealed class OnBoardingEvent : UiEvent {
        data class OnPageChanged(val position: Int, val lastIndex: Int) : OnBoardingEvent()
    }

    sealed class OnBoardingEffect : UiEffect {
        data class OnPageChanged(val position: Int) : OnBoardingEffect()
        data object NavigateToHome : OnBoardingEffect()
    }
}