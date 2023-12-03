package com.example.galleryapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repo: PicturesRepository): ViewModel() {

    val data = repo.pictureFlow.asLiveData()

}