package com.example.galleryapp.utils

sealed class Resource{
    data object Empty: Resource()
    data object Loading: Resource()
    data class Success<T>(val data: T): Resource()
    data class Error<T>(val message: String?): Resource()
}