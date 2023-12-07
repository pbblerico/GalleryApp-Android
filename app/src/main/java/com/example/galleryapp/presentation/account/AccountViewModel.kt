package com.example.galleryapp.presentation.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.data.models.User
import com.example.galleryapp.data.repositories.pictures.PicturesRepository
import com.example.galleryapp.data.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val currentUser: CurrentUserUseCase,
    private val repo: PicturesRepository,
) : ViewModel() {

    private var _images = MutableLiveData<List<Image>?>()
    val images: LiveData<List<Image>?> = _images

    private var _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    fun getCurrentUser(): FirebaseUser? {
        return currentUser.getCurrentUser()
    }

    fun getPublicImages(uid: String) {
        viewModelScope.launch {
            repo.getPublicImages(uid) { result ->
                when (result) {
                    is Resource.Success -> {
                        _images.value = result.data
                    }

                    is Resource.Failure -> {
                        Log.e(">>>", result.message.orEmpty())
                    }
                }
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            currentUser.getUserInfo {user ->
                when(user) {
                    is Resource.Success -> {
                        _user.value = user.data
                    }
                    is Resource.Failure -> {
                        Log.e(">>>", user.message.orEmpty())
                    }
                }
            }
        }
    }




}