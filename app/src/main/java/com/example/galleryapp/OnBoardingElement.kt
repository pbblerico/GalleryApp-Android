package com.example.galleryapp

import androidx.annotation.RawRes
import androidx.annotation.StringRes

enum class OnBoardingElement(
    @StringRes val text: Int,
    @RawRes val animation: Int
) {
    WELCOME(R.string.app_name, R.raw.welcome),
    GALLERY(R.string.name, R.raw.gallery),
    START(R.string.app_name, R.raw.start_dance)
}