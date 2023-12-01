package com.example.galleryapp.utils

sealed class Resource<T> {
//    class Empty<T>: Resource<T>()
    data class Success<T>(val data: T): Resource<T>()
    data class Error<T>(val message: String?): Resource<T>()
}