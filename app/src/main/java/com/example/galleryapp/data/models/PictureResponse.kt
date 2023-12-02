package com.example.galleryapp.data.models

import com.google.gson.annotations.SerializedName

data class PictureResponse(
    val page: Int? = null,
    @SerializedName("per_page")
    val perPage: Int? = null,
    val photos: List<Photo>? = null
)

data class Photo(
    val id: Int? = null,
    val url: String? = null,
    val photographer: String? = null,
    @SerializedName("photographer_url")
    val photograperUrl: String? = null,
)