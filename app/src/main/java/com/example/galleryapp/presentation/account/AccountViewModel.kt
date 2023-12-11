package com.example.galleryapp.presentation.account

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.galleryapp.data.models.Image
import com.example.galleryapp.data.models.User
import com.example.galleryapp.domain.useCases.authorization.CurrentUserUseCase
import com.example.galleryapp.domain.useCases.pictures.GetGalleryUseCase
import com.example.galleryapp.domain.useCases.user.UserInfoUseCase
import com.example.galleryapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    private val currentUser: CurrentUserUseCase,
    private val getGalleryUseCase: GetGalleryUseCase,
    private val getUserInfo: UserInfoUseCase
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
            getGalleryUseCase.getStoragePics(uid) {
                when(it) {
                    is Resource.Success -> {
                        _images.value = it.data
                    }

                    is Resource.Failure -> {
                        Log.d("asd", it.message.orEmpty())
                    }
                }
            }
        }
    }

    fun getUserInfo() {
        viewModelScope.launch {
            try {
                getCurrentUser()?.let {user ->
                    getUserInfo.execute(user.uid) {
                        when(it) {
                            is Resource.Success -> {
                                _user.value = it.data
                            }

                            is Resource.Failure -> {
                                Log.d("asd", it.message.orEmpty())
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                Log.d("asd", e.message.orEmpty())
            }
        }
    }




}