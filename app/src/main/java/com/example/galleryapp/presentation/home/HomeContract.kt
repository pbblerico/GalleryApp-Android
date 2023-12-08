package com.example.galleryapp.presentation.home

import com.example.galleryapp.utils.UiState

class HomeContract {

    sealed class HomeState: UiState {
        data object Idle: HomeState()
        data object Loading: HomeState()



    }

}