package com.example.galleryapp.presentation.image

import androidx.lifecycle.viewModelScope
import com.example.galleryapp.base.BaseViewModel
import com.example.galleryapp.data.preferences.Preferences
import com.example.galleryapp.data.preferences.PreferencesUtils
import com.example.galleryapp.domain.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.domain.useCases.pictures.GetPictureByIdUseCase
import com.google.firebase.database.DatabaseReference
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val getPictureById: GetPictureByIdUseCase,
    private val preferencesUtils: PreferencesUtils,
    private val getCurrentUser: CurrentUserUseCase,
    private val firebaseDatabase: DatabaseReference
) :
    BaseViewModel<ImageContract.ImageState, ImageContract.ImageEvent, ImageContract.ImageEffect>() {
    override fun createInitialState(): ImageContract.ImageState {
        return ImageContract.ImageState.Loading
    }

    fun isAuthorized(): Boolean {
        return preferencesUtils.getBoolean(Preferences.AUTHORIZED, false)
    }


    override fun handleEvent(event: ImageContract.ImageEvent) {
        when (event) {
            is ImageContract.ImageEvent.LoadImageFromApi -> {
                loadImage(event.id)
            }

            is ImageContract.ImageEvent.SaveImage -> {
                saveImage(event.id)
            }

            is ImageContract.ImageEvent.LoadImageFromUrl -> {
                setState(ImageContract.ImageState.LoadSuccess(photo = event.url))
            }
        }
    }

    private fun saveImage(id: Int?) {
        getCurrentUser.getCurrentUser()?.let {
            firebaseDatabase.child(it.uid).child("saved").child(id.toString()).setValue(id)
        }
    }

    private fun loadImage(id: Int?) {
        viewModelScope.launch {
            setState(ImageContract.ImageState.Loading)
            try {
                val result = id?.let { getPictureById.execute(it) }
                setState(ImageContract.ImageState.LoadSuccess(result?.urlSource?.original))
            } catch (e: Exception) {
                setState(ImageContract.ImageState.LoadFailure(e.message))
            }
        }
    }


}