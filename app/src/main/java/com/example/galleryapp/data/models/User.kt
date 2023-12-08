package com.example.galleryapp.data.models

import retrofit2.http.Url

data class User(
    val id: String? = null,
    val nickname: String? = null,
    val email: String? = null,
    @Url
    val avatar: String? = null,
)