package com.example.galleryapp

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val repo: PicturesRepository
): ViewModel() {

    fun getPictures() {
        viewModelScope.launch {
            try {
                val result = repo.getCuratedPictures()

            } catch (e: Exception) {
                Log.e("error", e.message ?: "unknown error")
            }
        }
    }

}