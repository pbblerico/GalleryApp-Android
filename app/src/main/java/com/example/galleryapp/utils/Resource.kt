package com.example.galleryapp.utils

class Resource {
    object Empty
    object Loading
    class Success<T>(data: T)
    class Error<T>(message: String, data: T? = null)
}