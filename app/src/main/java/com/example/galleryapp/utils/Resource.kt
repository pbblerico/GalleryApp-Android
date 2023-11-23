package com.example.galleryapp.utils

sealed class Resource<T> {
    data object Empty: Resource<Nothing>()
    data object Loading: Resource<Nothing>()
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val message: String?): Resource<T>()
}