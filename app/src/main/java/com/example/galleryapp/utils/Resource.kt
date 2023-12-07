package com.example.galleryapp.utils

sealed class Resource<T> {
    data class Success<T>(val data: T? = null): Resource<T>()
    data class Failure<T>(val message: String? = null): Resource<T>()
}