package com.example.galleryapp.data.models

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    val page: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    val photos: List<Photo>? = null,
    @SerializedName("total_results")
    val totalPages: Int,
)

data class Photo(
    val id: Int? = null,
    @SerializedName("src")
    val urlSource: UrlSource? = null,
    val photographer: String? = null,
)

data class UrlSource(
    val original: String? = null,
)