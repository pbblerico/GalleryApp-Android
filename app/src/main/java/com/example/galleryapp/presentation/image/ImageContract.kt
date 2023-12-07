package com.example.galleryapp.presentation.image

import com.example.galleryapp.utils.UiEffect
import com.example.galleryapp.utils.UiEvent
import com.example.galleryapp.utils.UiState

class ImageContract {

    sealed class ImageState: UiState {
        object Idle: ImageState()
        object Loading: ImageState()
        data class LoadSuccess(val photo: String?, val message: String? = null): ImageState()
        data class LoadFailure(val message: String?): ImageState()

    }

    sealed class ImageEvent: UiEvent {
        data class LoadImageFromUrl(val url: String): ImageEvent()
        data class LoadImageFromApi(val id: Int): ImageEvent()
        data class SaveImage(val id: Int): ImageEvent()
    }

    sealed class ImageEffect: UiEffect {
        data class ShowToast(val message: String?): ImageEffect()
    }

}